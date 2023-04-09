package com.example.springboot;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.repositories.MyDataRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;





@Controller
public class HeloController{
	
	@Autowired
	MyDataRepository repository;

	@Autowired
	MyDataDaoImpl dao;
	
	@PostConstruct
	public void init() {
		
		MyData d1 = new MyData();
		d1.setName("aaa");
		d1.setAge(123);
		d1.setMail("aaa@aaa");
		d1.setMemo("hello aaa");
		repository.saveAndFlush(d1);
		
		MyData d2 = new MyData();
		d2.setName("bbb");
		d2.setAge(234);
		d2.setMail("bbb@bbb");
		d2.setMemo("hello bbb");
		repository.saveAndFlush(d2);
		
		MyData d3 = new MyData();
		d3.setName("ccc");
		d3.setAge(456);
		d3.setMail("ccc@ccc");
		d3.setMemo("hello ccc");
		repository.saveAndFlush(d3);
		
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","this is sample content");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist",list);
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable int id, @ModelAttribute("formModel")MyData mydata, ModelAndView mav) {
		mav.setViewName("edit");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute("formModel")MyData mydata, ModelAndView mav) {
		repository.saveAndFlush(mydata);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteConfirm(@PathVariable int id, @ModelAttribute("formModel")MyData mydata, ModelAndView mav) {
		mav.setViewName("delete");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView delete(@ModelAttribute("formModel")MyData mydata, ModelAndView mav) {
		repository.deleteById(mydata.getId());
		
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	
}


class DataObject{
	private int id;
	private String name;
	private String value;
	public DataObject(int id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}