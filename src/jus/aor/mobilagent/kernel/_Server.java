package jus.aor.mobilagent.kernel;

import java.util.List;

public interface _Server {

	public void addService(String s1, String s2, String s3, Object[] o);
	
	public void deployAgent(String s1, Object[] o, String s2, List<String> sl1, List<String> sl2);
	
	public void deployAgent(String s1, Object[] o, String s2, List<ServiceDescriptor> l1);
	
}
