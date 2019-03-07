package common.rename;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldfile) {
		File newFile=null;
		do {
			long currentTime=System.currentTimeMillis();//밀리세컨초의 시간을 가져옴
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int rndNum=(int)(Math.random()*1000);
			
			String oldName=oldfile.getName();
			String ext="";//확장자 보관용
			int dot=oldName.lastIndexOf(".");
			if(dot>-1)
			{
				ext=oldName.substring(dot);
			}
			//새파일명생성
			String newName=sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			
			newFile=new File(oldfile.getParent(),newName);
						
		}while(!createNewFile(newFile));
		
		return newFile;
	}
	private boolean createNewFile(File newFile)
	{
		try {
			return newFile.createNewFile();
		}
		catch(IOException e)
		{
			return false;
		}
	}

}
