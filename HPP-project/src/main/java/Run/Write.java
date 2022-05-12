package Run;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import contamination.ChainOfContamination;

public class Write implements Runnable {
	static ArrayList<ChainOfContamination> listTop3 = new ArrayList();
	List<String[]>  writer = new ArrayList<>();
	BlockingQueue<ArrayList<ChainOfContamination>> outQueue_;
	Integer max_;
	
	public Write(BlockingQueue<ArrayList<ChainOfContamination>> outQueue,Integer max){
		outQueue_=outQueue;
		max_ = max;
	}

	
	
	public void run() {
		//Read from input Queue
		//Parse to csv
		//get top1_country_origin, top1_chain_root_person_id, top1_chain_score; top2_country_origin, top2_chain_root_person_id, top2_chain_score; top3_country_origin, top3_chain_root_person_id, top3_chain_score
		//Getting the top3, take()
		try {
			//we set the path which leads to the csv file to change
			String currentPath = System.getProperty("user.dir");
			String path = currentPath + "\\src\\main\\resources\\monitor.csv";
			 File outFile = new File(path);
		        outFile.getParentFile().mkdirs(); // Create parent folder.

		        // Create OutputStream to write a file.
		        OutputStream os = new FileOutputStream(outFile);

		        // Create a BufferedOutputStream with buffer array size of 16384 (16384 bytes = 16 KB).
		        BufferedOutputStream br = new BufferedOutputStream(os, 16384);

			
			
			Integer i =0;
			while (!(i.equals(max_)))
			{
			listTop3= outQueue_.take();
			i++;
			//we create the different elements that we will write
			String header ="";
			String header1 ="";
			String header2 ="";
			String record ="";
			String score ="";
			String record1 ="";
			String score1 ="";
			String record2 ="";
			String score2 ="";

			//we take the value that we will display
			if (Integer.valueOf(listTop3.get(0).getCountry()).equals(0)) {
				header = "France";
			}
			if (Integer.valueOf(listTop3.get(0).getCountry()).equals(1)) {
				header = "Italy";
			}
			if (Integer.valueOf(listTop3.get(0).getCountry()).equals(2)) {
				header = "Spain";
			}
			record = Integer.toString(listTop3.get(0).getRootId());
			score = Integer.toString(listTop3.get(0).getScore());
			
			if (listTop3.size()>1) {
			if (Integer.valueOf(listTop3.get(1).getCountry()).equals(0)) {
				header1 = "France";
			
			}
			if (Integer.valueOf(listTop3.get(1).getCountry()).equals(1)) {
				header1 = "Italy";
			
			}
			if (Integer.valueOf(listTop3.get(1).getCountry()).equals(2)) {
				header1 = "Spain";
			
			}
			record1 = Integer.toString(listTop3.get(1).getRootId());
			score1 = Integer.toString(listTop3.get(1).getScore());
			}
			
			
			if (listTop3.size()>2) {
			if (Integer.valueOf(listTop3.get(2).getCountry()).equals(0)) {
				header2 = "France";
			
			}
			if (Integer.valueOf(listTop3.get(2).getCountry()).equals(1)) {
				header2= "Italy";
			
			}
			if (Integer.valueOf(listTop3.get(2).getCountry()).equals(2)) {
				header2 = "Spain";
			
			}
			record2 = Integer.toString(listTop3.get(2).getRootId());
			score2 = Integer.toString(listTop3.get(2).getScore());
			}
			//we create the final string that we will flush in the csv file
			String subWriter = header+"  "+record+"  "+score+"  "+header1+"  "+record1+"  "+score1+"  "+header2+"  "+record2+"  "+score2+"  "+"\n";
			
			//we flush the final string in the csv file
			br.write(subWriter.getBytes("UTF-8"));
	        br.flush();
		}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	finally {
	}
		}
}
