package com.kh.common;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFileName) {

		String originName = originFileName.getName();
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int randNum = (int)((Math.random()*90000) + 10000);
		
		String exp = originName.substring(originName.lastIndexOf("."));
		
		String changeName = "HC" + currentTime + randNum + exp;
		
		File file = new File(originFileName.getParent(),changeName);
		
		return file;
	}

}
