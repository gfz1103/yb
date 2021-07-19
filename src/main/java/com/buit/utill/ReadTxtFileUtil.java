package com.buit.utill;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.buit.commons.BaseException;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpjcxx;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxAddReq;

/**
 * @author 老花生
 */
public class ReadTxtFileUtil {

	public static String lineTxt = null;

	public static String splitIt(String str, int end) {
		String temp = null;
		// 记录已经截取的字节
		StringBuffer sb = new StringBuffer();
		// 记录已经截取的字节长度
		int len = 0;
		// 中文数
		int zhNum = 0;

		for (int i = 0; i < end && len < end; i++) {
			if(str.length()>i){
				// 获取单个字符
				temp = String.valueOf(str.charAt(i));
				// 获取单个字符的字节数,累加
				try {
					int num = temp.getBytes("gbk").length;
					if (num == 2) {
						zhNum++;
					}
					len += num;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				// 拼接到目标字符串上
				sb.append(temp);
			}else{
				lineTxt=lineTxt+" ";
			}
		}
		lineTxt = lineTxt.substring((end - zhNum));
		return sb.toString();
	}

	public static List<List<String>> readTxt(MultipartFile file, Integer[] widths) {
		if (!file.isEmpty()) {
			try {
				return ReadTxtFileUtil.readTxt(file.getInputStream(), widths);
			} catch (IOException e) {
				throw BaseException.create("ERROR_SHYB_0010");
			}
		} else {
			throw BaseException.create("ERROR_SHYB_0010");
		}
	}
	public static List<List<String>> readTxt(InputStream inputStr, Integer[] widths) {
		List<List<String>> ret = new ArrayList<>();
		try {
			InputStreamReader isr = new InputStreamReader(inputStr, "gbk");
			BufferedReader br = new BufferedReader(isr);

			while ((lineTxt = br.readLine()) != null) {
				System.out.println(lineTxt);
				List<String> obs = new ArrayList<>();
				for (Integer width : widths) {
					obs.add(splitIt(lineTxt, width));
				}
				ret.add(obs);
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw BaseException.create("ERROR_SHYB_0011");
		}

		return ret;
	}
	public static void main(String[] args) throws FileNotFoundException {
    	/*Integer[] widths = new Integer[] {15, 8, 1, 20, 20, 80, 80, 20, 80, 100, 80, 20, 16, 40, 40, 300, 32, 100, 200, 8, 8, 100};
    	List<List<String>> ret = ReadTxtFileUtil.readTxt(new FileInputStream("D:\\work\\his\\doc\\上海医保数据\\整理后\\药品品规基础信息\\YPJCXX_00871_20200319.txt"), widths);
		for(List<String> obj : ret) {
			System.out.println(obj.get(16).trim());
			System.out.println(obj.get(16).trim().charAt(3));
    	}*/

		String str="                  2020061820991231乙类                                                              ";
		System.out.println(str.length());
		System.out.println(str.substring((100 - 2)));
	}

}
