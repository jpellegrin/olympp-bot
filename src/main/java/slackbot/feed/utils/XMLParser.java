package slackbot.feed.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//@Component
public class XMLParser<T> {

	private JAXBContext jaxbContext;
	private Class<T> tClass;

	// @Autowired
	// private T t;

	public XMLParser(Class<T> tClass) {
		this.tClass = tClass;
	}

	public T parse(String xmlDocument) {
		T t = null;
		try {
			jaxbContext = JAXBContext.newInstance(tClass);
			// jaxbContext = JAXBContext.newInstance(t.getClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(xmlDocument);
			t = (T) unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return t;
	}

}
