package dev;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class CopyANTLR4GrammarToPackages {

	public static void main(String[] args) throws IOException {
		File dir = new File("target/generated-sources/antlr4");
		for(File f : dir.listFiles()) {
			if(f.getName().endsWith(".java")) {
				String data = Files.lines(FileSystems.getDefault().getPath(f.getAbsolutePath())).collect(Collectors.joining("\r\n"));
				data = "package com.droom.grammar;\r\n\r\n" + data;
				FileWriter writer = new FileWriter(new File("src/com/droom/grammar/" + f.getName()));
				writer.write(data);
				writer.close();
			}
		}
	}
}
