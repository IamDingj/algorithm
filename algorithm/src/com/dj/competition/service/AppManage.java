/**   
 * @Project: competition 
 * @Title: AppManage.java 
 * @Package com.yanxintec.competition 
 * @Description: TODO 
 * @author dingj 
 * @date 2018年7月16日 下午5:14:05 
 * @Copyright: 2018 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package com.dj.competition.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.dj.competition.common.AVLTree;
import com.dj.competition.common.Node;
import com.dj.competition.common.User;
import com.dj.competition.util.PropertiesUtil;
import com.dj.competition.util.SortUtil;

/** 
 * @ClassName AppManage  
 * @Description 应用管理类 
 * @author dingj 
 * @date 2018年7月16日  
 *   
 */
public class AppManage {
	
	private String readFilePath = PropertiesUtil.getValue("readFilePath");
	
	public Map<Integer, Integer> getIdByReadCVS(String userId) {
		BufferedReader reader = null;
		String line = null;
		int count = 0;
		int rankScore = 0;
		try {
			reader = new BufferedReader(new FileReader(readFilePath));
			while ((line = reader.readLine()) != null) {
				count++;
				// CSV格式文件为逗号分隔符文件，这里根据逗号切分
				String item[] = line.split(",");
				String uid = item[0];
				int score = Integer.parseInt(item[1]);
				if (userId.equals(uid)) {
					// 缓存 score
					rankScore = score;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(count, rankScore);
		return map;
	}
	
	/** 
	 * @Title: getRankByUserId 
	 * @Description: 给定用户的信用评分名次 
	 * @param userId
	 * @return 参数说明
	 * @return int    返回类型
	 */
	public void getRankByUserId(String userId) {
		AVLTree tree = new AVLTree();
		Node node = new Node();
		node = tree.createTree(node, 999999, 0);
		int count = 0;
		int temp = 0;
		int sum =0;
		int rankScore =0;
		Map<Integer, Integer> map2 = getIdByReadCVS(userId);
		for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
			sum = entry.getKey();
			rankScore = entry.getValue();
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(readFilePath));
			String line = null;  
	        while((line=reader.readLine())!=null){  
	        	count++;
	        	//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	            String item[] = line.split(",");
	            int score = Integer.parseInt(item[1]);
	            tree.addNewScore(node, score);
	            if(count<sum&&score==rankScore){
	            	temp++;
	            }
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int rank = tree.getRank(node,rankScore,1);
        rank += temp; 
        System.out.println(userId+" 的排名为： "+rank);
	}
	
	
	
	/** 
	 * @Title: getUserIdByScore 
	 * @Description: Top 100 的信用评分的用户   参数说明
	 * @return void    返回类型
	 */
	public void getUserIdByScore(){
		List<User> list = new ArrayList<>(16);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(readFilePath));
			String line = null;  
	        while((line=reader.readLine())!=null){  
	        	//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	            String item[] = line.split(",");
	            String  userId = item[0];
	            int score = Integer.parseInt(item[1]);
	            list.add(new User(userId, score));
            	list = SortUtil.sortList(list);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("--------- Top 100 的信用评分的用户  start ----------");
		for (User user : list) {
			System.out.println(user.toString());
		}
		System.out.println("--------- Top 100 的信用评分的用户  end ----------");
	}
	
	/** 
	 * @Title: getScoreByCount 
	 * @Description: 信用评分重复次数最高的10个信用评分
	 * @param tree
	 * @param node 参数说明
	 * @return void    返回类型
	 */
	public void getScoreByCount(){
		AVLTree tree = new AVLTree();
		Node node = new Node();
		node = tree.createTree(node, 999999, 0);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(readFilePath));
			String line = null;  
	        while((line=reader.readLine())!=null){  
	        	//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	            String item[] = line.split(",");
	            int score = Integer.parseInt(item[1]);
	            tree.addNewScore(node, score);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(16);
		map = tree.postOrderTraversal(node, map);
		System.out.print("重复次数最高的10个信用评分为：");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("score="+entry.getKey()+", count="+entry.getValue());
		}
	}

}
