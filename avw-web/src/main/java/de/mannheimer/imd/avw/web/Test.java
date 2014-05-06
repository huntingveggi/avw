package de.mannheimer.imd.avw.web;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Test implements ITest {

	private String name = "was solld as";

	/* (non-Javadoc)
	 * @see de.mannheimer.imd.avw.web.ITest#getName()
	 */
	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

}
