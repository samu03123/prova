package com.sistemi.informativi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemi.informativi.entity.Tutorial;
import com.sistemi.informativi.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService{

	@Autowired
	private TutorialRepository tutorialRepository;
	
	@Override
	public List<Tutorial> findAllTutorials() throws Exception {
		
		List<Tutorial> tutorials=new ArrayList<>();
		tutorials= tutorialRepository.findAll();
		
		if(tutorials.isEmpty()) {
			
			throw new Exception("Tutorials list empty");
		}
		return tutorials;
	}

	@Override
	public Tutorial findTutorialById(int id) throws Exception {
		
		return tutorialRepository.findById(id).orElseThrow(()-> new Exception("Tutorial not found"));
	}

	@Override
	public Tutorial saveOrUpdateTutorial(Tutorial tutorial) throws Exception {
		
		Tutorial saveOrUpdated= tutorialRepository.save(tutorial);
		
		if(!saveOrUpdated.getName().equals(tutorial.getName())) {
			
			throw new Exception("Insert or Update Failed");
		}
		return saveOrUpdated;
	}

	@Override
	public Map<String, Boolean> deleteTutorial(int id) {
		
		Map<String, Boolean> deletionMap= new HashMap<>();
		
		try {
			tutorialRepository.deleteById(id);
			
			deletionMap.put("deletion", true);
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			
			deletionMap.put("deletion", false);
		}
		return deletionMap;
	}

}
