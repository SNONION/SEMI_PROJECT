package com.kh.common;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class ShopFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFileName) {
		
		return originFileName;
	}

}
