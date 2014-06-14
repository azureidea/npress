package nc.liat6.npress.cache;

import java.io.File;
import java.lang.reflect.Method;

import nc.liat6.frame.aop.AopSucceedManager;
import nc.liat6.frame.log.Logger;
import nc.liat6.frame.web.WebContext;

/**
 * »º´æAOP
 * @author 6tail
 *
 */
public class CacheAOP extends AopSucceedManager{

	public CacheAOP(){
		super("*","add,modify,delete,update");
	}

	@Override
	public void execute(Object o,Method m,Object[] args){
		File dir = new File(WebContext.REAL_PATH,"cache");
		if(!dir.exists() || !dir.isDirectory()){
			dir.mkdirs();
		}
		File[] fs = dir.listFiles();
		for(File f:fs){
			f.delete();
		}
		Logger.getLog().debug("Çå¿Õ»º´æÎÄ¼þ");
	}

}
