package br.com.projeto.bean.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = "session")
@ManagedBean(name = "checkboxView")
public class CheckboxView {

	private String[] selectedCities;

	private List<String> cities;

	
	@PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities.add("Miami");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");
        cities.add("Brasilia");
        cities.add("Amsterdam");
        
	}
	
	public String[] getSelectedCities() {
		return selectedCities;
	}

	public void setSelectedCities(String[] selectedCities) {
		this.selectedCities = selectedCities;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "CheckboxView [selectedCities=" + Arrays.toString(selectedCities) + ", cities=" + cities + "]";
	}

	
}
