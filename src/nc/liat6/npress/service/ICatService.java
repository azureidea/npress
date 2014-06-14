package nc.liat6.npress.service;

import java.util.List;

import nc.liat6.npress.bean.Cat;

/**
 * ����ҵ��ӿ�
 * @author 6tail
 *
 */
public interface ICatService{
	
	/**
	 * ��ȡ�����б�
	 * @return �����б�
	 */
	public List<Cat> listCats();
	
	/**
	 * ����ID��ȡ����
	 * @param id ����ID
	 * @return ����
	 */
	public Cat getCat(long id);

}
