package org.community.CrazyLabs.Builder;

import java.util.ArrayList;
import java.util.Random;

public class ModuleForm {
	
	//up, down, left, right, front, back
	private ArrayList<Module> modules; 
	
	public ModuleForm ()
	{
		this.modules = new ArrayList<Module>();
	}
	
	public ModuleForm (Module modules)
	{
		this.modules = new ArrayList<Module>();
		this.modules.add(modules);
	}
	
	public void addModule(Module m)
	{
		this.modules.add(m);

	}
	
	public Module getOneModule()
	{
		if(modules.size() == 0)
			return null;
		Random r = new Random();
		return modules.get((int) (r.nextFloat()/(1.0f/(float)modules.size())));
	}
}
