package de.mannheimer.imd.avw.web;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import de.mannheimer.imd.avw.web.impl.ListWrapper;
import de.mannheimer.imd.avw.web.impl.RequestMappingMethod;

@Controller
public class WadlController {

	@Inject
	ApplicationContext context;

	RequestMappingHandlerMapping mapping;

	static final Logger logger = LoggerFactory.getLogger(WadlController.class);

	@RequestMapping(value = "/wadl", method = RequestMethod.GET)
	public String create(ModelMap model) {

		ListWrapper wrapper = new ListWrapper();
		Map<RequestMappingInfo, HandlerMethod> map = getMapping()
				.getHandlerMethods();

		for (RequestMappingInfo m : map.keySet()) {
			RequestMappingMethod newM = new RequestMappingMethod();
			HandlerMethod method = map.get(m);

			newM.setMethodName(m.toString());

			wrapper.getMethods().add(newM);
		}
		model.addAttribute("model", wrapper);
		
		return "api";

	}

	public RequestMappingHandlerMapping getMapping() {

		if (mapping == null) {
			mapping = context.getBean(RequestMappingHandlerMapping.class);
		}
		return mapping;
	}

	public void setMapping(RequestMappingHandlerMapping mapping) {

		this.mapping = mapping;
	}

}
