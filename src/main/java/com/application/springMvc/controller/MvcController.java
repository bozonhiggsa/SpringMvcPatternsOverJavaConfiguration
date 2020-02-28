package com.application.springMvc.controller;

import com.application.springMvc.model.Employee;
import com.application.springMvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Controller for requests handling
 * @author Ihor Savchenko
 * @version 1.0
 */
@Controller
public class MvcController {

    @Autowired
    ServletConfig servletConfig;

    @Autowired
    ServletContext servletContext;

    /*@Autowired
    HttpServletRequest httpRequest;*/

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/reference/{ref}")
    public String choosePage(@PathVariable("ref") int ref){
        if(ref == 1){
            return "redirect:/page1";
        } else if(ref == 2){
            return "redirect:/page2";
        } else if(ref == 3){
            return "redirect:/page3";
        } else if(ref == 4){
            return "redirect:/page4";
        }
        return null;
    }

    @RequestMapping(value = "page1", method = RequestMethod.GET)
    public String renderingPage1(Model model) {
        User user = new User();
        user.setLogin("default");
        user.setPassword("default");
        user.setLevel(0);
        model.addAttribute("user", user);
        return "user1";
    }

    @RequestMapping(value = "/addUser1", method = RequestMethod.POST)
    public String createUser1(@ModelAttribute("user") User user,
                             Model model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("level", user.getLevel());

        return "/result1";    }

    @RequestMapping(value = "page2", method = RequestMethod.GET)
    public String renderingPage2() {
        return "user2";
    }

    @RequestMapping(value = "/addUser2", method = RequestMethod.POST)
    public String createUser2(@RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "level") int level,
                              Model model) {
        model.addAttribute("login", login);
        model.addAttribute("password", password);
        model.addAttribute("level", level);

        return "/result2";    }

    @RequestMapping(value = "page3", method = RequestMethod.GET)
    public ModelAndView renderingPage3() {
        return new ModelAndView("user3", "user", new User());
    }

    @RequestMapping(value = "/addUser3", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("user") User user,
                             ModelMap model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("level", user.getLevel());

        return "/result3";
    }

    @RequestMapping(value = "page4", method = RequestMethod.GET)
    public String renderingPage4() {
        return "user4";
    }

    @GetMapping(value = "/requestParams")
    public String returnParams(@RequestParam String login, @RequestParam String password,
                             ModelMap model) {
        model.addAttribute("login", login);
        model.addAttribute("password", password);

        return "/result6";
    }

    @RequestMapping("/handle1")
    public HttpEntity<String> handle1() {
        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println("CacheControl: " + responseHeaders.getCacheControl());
        System.out.println("Accept: " + responseHeaders.getAccept());
        System.out.println("Connection: " + responseHeaders.getConnection());
        System.out.println("Date: " + responseHeaders.getDate());
        System.out.println("ETag: " + responseHeaders.getETag());
        System.out.println("Pragma: " + responseHeaders.getPragma());
        System.out.println("Expires: " + responseHeaders.getExpires());
        System.out.println("Allow: " + responseHeaders.getAllow());
        System.out.println("Current time: " + new Date(System.currentTimeMillis()));
        responseHeaders.set("MyResponseHeader", "MyValue");
        responseHeaders.setCacheControl("private, max-age=0, must-revalidate");
        responseHeaders.setExpires(System.currentTimeMillis() + 3600000);
        responseHeaders.setPragma("must-revalidate, max-age=1000");
        responseHeaders.setLastModified(System.currentTimeMillis());
        System.out.println("LastModified: " + responseHeaders.getLastModified());

        return new HttpEntity<String>("Hello World", responseHeaders);
    }

    @RequestMapping("/handle2")
    public ResponseEntity<String> handle2() {
        URI uri = URI.create("/found");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        responseHeaders.setLocation(uri);
        return new ResponseEntity<String>("Hello World", responseHeaders,
                HttpStatus.FOUND);
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public String handler() {
        return "/result4";
    }

    @RequestMapping("/handle3")
    public String handle3(HttpServletRequest httpRequest) {
        System.out.println("got access to ServletConfig. ServletName retrieved: " + servletConfig.getServletName());
        System.out.println("got access to ServletContext from HttpServletRequest. ContextPath retrieved: " + httpRequest.getServletContext().getContextPath());
        System.out.println("got access to HttpServletRequest. Request method retrieved: " + httpRequest.getMethod());
        System.out.println("got access to HttpServletRequest. Scheme retrieved: " + httpRequest.getScheme());
        System.out.println("got access to HttpSession. idSession retrieved: " + httpSession.getId());
        System.out.println("got access to HttpSession. idSession retrieved: " + httpRequest.getSession().getId());
        RequestContext requestContext = new RequestContext(httpRequest);
        System.out.println("RequestContext created and a locale retrieved: " + requestContext.getLocale());
        System.out.println("RequestContext created and a theme retrieved: " + requestContext.getTheme());
        System.out.println("RequestContext created and a timeZone retrieved: " + requestContext.getTimeZone());

        return "/result5";
    }

    @RequestMapping(value = "/handle4", method = RequestMethod.GET)
    public RedirectView performRedirect() {
        //return new RedirectView(String.format("%s%s",servletContext.getContextPath(),"/found"), true, false);
        return new RedirectView("/found", true, false);
    }

    @RequestMapping(value = "/handle5", method = RequestMethod.GET)
    public String redirectExample(HttpServletRequest request) {
        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
        return "forward:/handle4";
    }

    @RequestMapping(value = "/employeesContacts/{contactNumber}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeeByContactNumber(
            @MatrixVariable(required = true) String contactNumber) {
        List<Employee> employeesList = new ArrayList<Employee>();
        employeesList.add(new Employee(1, "Ihor", contactNumber));
        return new ResponseEntity<List<Employee>>(employeesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{name}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployeeByNameAndContactNumber(
            @PathVariable String name,
            @MatrixVariable String contactNumber) {
        List<Employee> employeesList = new ArrayList<Employee>();
        employeesList.add(new Employee(2, name, contactNumber));
        return new ResponseEntity<List<Employee>>(employeesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/companyEmployee/{company}/employeeData/{employee}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getEmployeeAndCompany(
            @MatrixVariable(pathVar = "company") Map<String, String> company,
            @MatrixVariable(pathVar = "employee") Map<String, String> employee) {
        Map<String, String> map = new HashMap<String, String>(company);
        map.putAll(employee);
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/exceptionHandler", method = RequestMethod.GET)
    public String exceptionHandlingExample() throws IOException {

        if(true){
            throw new IOException();

        }
        return "index";
    }

}
