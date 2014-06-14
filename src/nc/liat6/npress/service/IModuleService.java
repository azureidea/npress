package nc.liat6.npress.service;

import java.util.List;

import nc.liat6.npress.bean.Module;

/**
 * ģ��ҵ��ӿ�
 * @author 6tail
 *
 */
public interface IModuleService{
	
	/**
	 * ��ȡģ���б�
	 * @return ģ���б�
	 */
	public List<Module> listModules();

	/**
	 * ����ID��ȡģ��
	 * @param id ģ��ID
	 * @return ģ��
	 */
	public Module getModule(long id);
}
