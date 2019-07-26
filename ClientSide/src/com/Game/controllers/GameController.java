package com.Game.controllers;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.Tomboliere;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
// import com.util.TextToSpeech;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameController {

	private Player p;
	private ArrayList<Integer> extractions;
	private HashMap<String,String> winnings;
	private Thread estrattore;
	private int n;
	private String ipaddress = "localhost";
	private int lastNum;
	private String lastWinningPhrase;

	//Texttospeech
	//private TextToSpeech tts;

	public GameController(String playerName, int n) {

		String playerJson = connectHttpTo("http://"+ipaddress+":8282/addplayer?U=" + playerName + "&N=" + n);

		//tts = new TextToSpeech();
		//tts.setVoice("istc-lucia-hsmm");


		extractions = new ArrayList<>();
		winnings = new HashMap<>();
		p = deserializePlayer(playerJson,n);


		this.n = n;

		//tts.speak("Ciao " + p.getUsername(),1.0f,false,false);


	}

	private Player deserializePlayer(String playerJson, int n) {
		//Parsing json
		Any anyClass = JsonIterator.deserialize(playerJson);

		String uName = String.valueOf(anyClass.get("username"));

		Any cards = anyClass.get("cartelle");

		Player p = new Player(uName);

		for (int i = 0; i < n; i++) {
			Any card = cards.get(i);
			String numbers = String.valueOf(card.get("numeri"));
			ArrayList<Integer> numArr = string2Array(numbers);

			p.addCartella(new Cartella(numArr));

		}



		return p;

	}

	private ArrayList<Integer> string2Array(String numbers) {
		String num = numbers.substring(1,numbers.length()-1);
		String[] nums = num.split(",");

		ArrayList<Integer> numsArr = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			numsArr.add(Integer.valueOf(nums[i]));
		}


		return numsArr;
	}

	private String  connectHttpTo(String url) {

		try {
			URL connectionUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) connectionUrl.openConnection();
			connection.setRequestMethod("GET");

			int rcode = connection.getResponseCode();

			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + rcode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//return result
			return response.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void startExtraction(Runnable updateFunction) {
		estrattore = new Thread(() -> {

			while (true){

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String nums = connectHttpTo("http://"+ipaddress+":8282/extractions");

				Any anyNums = JsonIterator.deserialize(nums);
				extractions = string2Array(String.valueOf(anyNums.get("numbers")));

				String wins = connectHttpTo("http://"+ipaddress+":8282/winnings");
				Any anyWins = JsonIterator.deserialize(wins);
				String winners = anyWins.get("winners").toString();
				System.out.println(winners);
				winners=winners.substring(1,winners.length()-1);
				takeWinningUser(winners,winnings);

				if(!extractions.isEmpty() && extractions.get(extractions.size()-1) != lastNum) {
					//Reproduce audio and update last num
					lastNum = extractions.get(extractions.size()-1);

					String bip = "src/resources/BipSound.mp3";
					Media hit = new Media(new File(bip).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();


					//tts.speak(String.valueOf(lastNum),1.0f,false,false);
				}


				updateFunction.run();
			}
		});

		estrattore.start();
	}

	public void stopExtractions(){
		estrattore.interrupt();
		estrattore.stop();
	}


	public Cartella getCartella(int index) {
		return p.getCartella(index);
	}
	public Integer[] getCartellaAsArray(int index) {return p.getCartella(index).getNumeri();}

	public boolean buttonControl(CallEnum callEnum, int iCartella) throws NullPointerException {
		String resp;
		resp=connectHttpTo("http://"+ipaddress+":8282/checkcard?U=" +p.getUsername()+ "&C="+iCartella +"&CT=" +callEnum.name()+ "&LN="+extractions.get(extractions.size()-1));

		if(resp!=null && resp.equals("{\"check\":\"true\"}")){
			return true;
		}else{
			return false;
		}

	}

	public ArrayList<Integer> getExtractions() {
		return extractions;
	}

	public int getCartellaCount() {
		return n;
	}


	private void takeWinningUser (String jsonwin,HashMap winnings){
		String[] win= jsonwin.split(",");




			for (int i = 0; i < win.length; i++) {
				String[] userWin = win[i].split(":");
				if (userWin.length == 2) {
					winnings.put(userWin[1], userWin[0]); //TODO modificare in modo da ottenere tramite la chiave il nome utente
					lastWinningPhrase = userWin[0] + " ha fatto " + userWin[1];
				}
			}

	}

	public HashMap<String, String> getWinnings() {
		return winnings;
	}

	public String getPlayerName() {
		return p.getUsername();
	}

	public String getLastWinningPhrase() {
		return lastWinningPhrase;
	}
}
