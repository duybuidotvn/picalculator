package com.tma.picalculator.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tma.picalculator.domain.PiModel;
import com.tma.picalculator.service.PiCalculatorService;
import com.tma.picalculator.web.exception.PiCalculatorException;
/**
 * The PiController class is a part of presentation layer which provides access to the application behaviors which 
 * are defined by PiCalculationService interface. The PiController interpret 
 * user input and transform such input into the sensible model(in this class 
 * will be piModel) which will be represented to the user by the view. 
 * 
 * The @Controller used to mark the class as a controller class. It annotation 
 * acts as a stereotype for the annotated class. The dispatcher scans 
 * such annotated classes for mapped the methods and detects @RequestMapping 
 * annotations. And, using the @Autowired annotation the container can wire the
 * beans automatically ( in this class being with PiCalculatorService.)
 * 
 * @author duybui
 * 
 */
@Controller
public class PiController {

	@Autowired
	PiCalculatorService calService;
	/**
	 * This method just mapping the index page to a handler method getPi 
	 * and return page getPi.jsp as the main page
	 * @param: model 
	 * @return: render getPi.jsp page.
	 */
	@RequestMapping(value = "/pi")
	public String getPi(Model model) throws Exception {
		model.addAttribute("piModel", new PiModel());
		return "getPi";
	}
	
	@RequestMapping(value = "/pi/cal.do", method = RequestMethod.POST )
	public String home(Model model) throws Exception {
		model.addAttribute("piModel", new PiModel());
		return "getPi";
	}
	
	/**
	 * This method get action from link /pi/cal.do, 
	 * add parameters to piModel; validate parameters; 
	 * execute function in services to get result.
	 * The GET method has a further @RequestMapping refinement: it's only
	 * accepts GET requests.
	 *     
	 * @param: piModel refers the properties of the Pi object
	 * @param: bindingResult validate results for form objects following 
	 * 		   the condition from PiModel
	 * @param: model use to add attribute errMsg, return the view name as inform string.
	 * @return: render test.jsp page.
	 */
	@RequestMapping(value = "/pi/cal.do", method = RequestMethod.GET)
	public String getPi(@Valid @ModelAttribute("piModel") PiModel piModel,
			BindingResult bindingResult, Model model) throws Exception {		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errMsg", "Please input positive number from 0 to 100 billions!");
			return "getPi";
		} else {
			calService.getPi(piModel);
		}
		return "getPi";
	}
	/**
	 * Handles PiCalculatorException
	 * @param e Thrown exception while processing PI
	 * @return binds error message and return to error page
	 */
	@ExceptionHandler(PiCalculatorException.class)
	public ModelAndView handleGetPiException(PiCalculatorException ex) {
	    ModelMap model = new ModelMap();
	    model.put("errMsg", ex.getMessage());
	    return new ModelAndView("getPi", model);
	}
}
