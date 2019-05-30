package com.Game.controllers;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.Tomboliere;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController {

	private Player p;
	private ArrayList<Integer> extractions;
	private Thread estrattore;
	private int n;

	public GameController(String playerName, int n) {

		String playerJson = connectHttpTo("http://localhost:8282/addplayer?U=" + playerName + "&N=" + n,true);

		extractions = new ArrayList<>();

		p = deserializePlayer(playerJson,n);


		this.n = n;




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

	private String  connectHttpTo(String url,boolean a) {

		try {
			String rcodetostring;
			URL connectionUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) connectionUrl.openConnection();
			connection.setRequestMethod("GET");

			int rcode = connection.getResponseCode();

			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + rcode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			rcodetostring = String.valueOf(rcode);
			//return result
			if(a)
			{return response.toString();}
			else {return rcodetostring;}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void startExtraction(Runnable updateFunction) {
		estrattore = new Thread(() -> {

			while (true){

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String nums = connectHttpTo("http://localhost:8282/extractions",true);

				Any anyNums = JsonIterator.deserialize(nums);
				extractions = string2Array(String.valueOf(anyNums.get("numbers")));


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
		resp=connectHttpTo("http://localhost:8282/checkcard?U=" +p.getUsername()+ "&C="+iCartella +"&CT=" +callEnum.name()+ "&LN="+extractions.get(extractions.size()-1),false);

		if(resp != null && resp.equals("200") )
		{return true;}
		else
		{return false;}

	}

	public ArrayList<Integer> getExtractions() {
		return extractions;
	}

	public int getCartellaCount() {
		return n;
	}
}
