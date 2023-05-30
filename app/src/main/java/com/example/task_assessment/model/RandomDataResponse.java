package com.example.task_assessment.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RandomDataResponse{

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"RandomDataResponse{" + 
			"categories = '" + categories + '\'' + 
			"}";
		}


}