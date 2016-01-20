package com.hm.engine.common.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hm.engine.common.ConfigUtil;
import com.hm.engine.common.Constants;
import com.hm.engine.common.Utils;

public class LoggerFactory {
	static Logger log=LoggerFactory.getLogger(LoggerFactory.class);


	public static final <T> Logger getLogger(Class<T> clazz) {
		return new Logger(clazz.getName());
	}

	public static final <T> Logger getLogger(String message) {
		return new Logger(message);
	}

	public static final Logger getLogger() {
		return new Logger();
	}

	public static class Logger {
		final static String dir = ConfigUtil.getByKey("logger.dir");


		final static Charset ENCODING = StandardCharsets.UTF_8;
		String prefix;


		public Logger(String message) {
			this.prefix = message;
		}

		public Logger() {
			super();
		}



		void writeSmallTextFile(String aLines, String aFileName)
				throws IOException {
			Path path = Paths.get(aFileName);
			OutputStream out = Files.newOutputStream(path,StandardOpenOption.APPEND);
			try (BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(out, ENCODING))) {
				writer.append(aLines);
				writer.newLine();
			}

		}

		public static String LEVEL=ConfigUtil.getByKey("logger.level");


		public boolean isInfo(){
			if(LEVEL!=null&&LEVEL.equals("INFO")){
				return true;
			}else{
				return false;
			}
		}

		public  void INFO(String message) {
			if(!isInfo()){
				return;
			}
			createFile();
			try {
				if(StringUtils.isNotBlank(this.prefix)){
					message=this.prefix +" :"+message;
				}
				writeSmallTextFile(message, getFilePath());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void createFile(){
			if(!Files.exists(Paths.get(dir))){
				try {
					Files.createDirectories(Paths.get(dir));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(!Files.exists(Paths.get(getFilePath()))){
				try {
					Files.createFile(Paths.get(getFilePath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		public static void main(String[] args){
			LoggerFactory factory =new LoggerFactory();
			log.createFile();
		}

		public  String getFilePath(){
			return  dir+"engine_"
					+ Constants.DATE_PARSE_FORMAT.format(new Date(Utils
					.getStartTime())) + ".log";
		}
	}
}
