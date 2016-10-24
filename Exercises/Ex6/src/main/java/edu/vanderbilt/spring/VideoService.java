package edu.vanderbilt.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoService {

	private VideoRepository videos_;
	
	@Autowired
	public VideoService(VideoRepository repo) {
		videos_ = repo;
	}
	
	//From the assignment page:
	//Curl requests
	// curl -i -X POST -H "Content-Type:application/json" -d "{\"id\":-1}" http://localhost:8080/video
	// curl -i -X POST -H "Content-Type:application/json" -d "{\"id\":-1,\"name\":\"Rocky\"}" http://localhost:8080/video
	
	//GET video id /video/{id}
	@RequestMapping(value="/video/{id}", method=RequestMethod.GET)
	public Video getVideo(@PathVariable("id") Long videoId){
		System.out.println("id:"+videoId);
		return videos_.findOne(videoId);
	}
	
	//POST Video  /video 
	@RequestMapping(value="/video", method=RequestMethod.POST, consumes="application/json")
	public void addVideo(@RequestBody Video video){
		videos_.save(video);
	}

	
	//Get /video returns a JSON list of videos 
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public Iterable<Video> listOfVideos(){
		return videos_.findAll();
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.DELETE)
	public void deleteVideo(@PathVariable("id") Long videoId){
		videos_.delete(videoId);
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.POST, consumes="application/json")
	public void updateVideo(@PathVariable("id") Long videoId, @RequestBody Video video){
		video.setId(videoId);
		videos_.save(video);
	}
	
}
