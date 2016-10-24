package edu.vanderbilt.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseApplicationTests {

	@Autowired
	VideoService videoService;
	
	@Test
	public void contextLoads() {	
	}
	
	@Test
	public void videoServiceLoaded() {
		assertNotNull(videoService);
	}
	
	
	//Test For /video --> should return a list of videos
	@Test
	public void getReturnsAListOfVideos() {
		Video video = new Video();
		videoService.addVideo(video);
		//Get list of videos
		Iterable<Video> videos = videoService.listOfVideos();
		assertNotNull(videos);
		assertEquals(videos.iterator().next().getClass(), Video.class);
	}
	
	
	//Test for GET /video/{id}
	@Test
	public void getVideoReturnsVideo(){
		Video video = new Video();
		videoService.addVideo(video);
		Video videoFromService = videoService.getVideo(video.getId());
		assertNotNull(videoFromService);
		assertEquals(videoFromService.getId(),video.getId());	
	}
	
	//Test for DELETE /video/{id}
	@Test
	public void testDelete(){
		//Add a video
		Video video = new Video();
		videoService.addVideo(video);
		Video videoFromService = videoService.getVideo(video.getId());
		assertNotNull(videoFromService);
		assertNotEquals(videoFromService,video);
		videoService.deleteVideo(video.getId());
		//Delete video and video for the that id should be null
		assertNull(videoService.getVideo(video.getId()));
	}
	
	//Test for POST /video
	@Test
	public void testPostVideo(){
		Video video = new Video();
		video.setName("Test Movie");
		video.setSize(10);
		video.setGenre("Test Genre");
		video.setUrl("Test URL");
		
		//Add a video and check if the values are the same 
		videoService.addVideo(video);
		Video videoFromService = videoService.getVideo(video.getId());
		assertEquals(videoFromService.getId(),video.getId());
		assertEquals(videoFromService.getName(),video.getName());
		assertEquals(videoFromService.getSize(),video.getSize());
		assertEquals(videoFromService.getGenre(),video.getGenre());
		assertEquals(videoFromService.getUrl(),video.getUrl());
	}
	
	//Test for POST /video/{id} 
	@Test
	public void testPostVideoUpdate(){
		//Add a video
		Video video = new Video();
		video.setName("Test Movie");
		video.setSize(10);
		video.setGenre("Test Genre");
		video.setUrl("Test URL");
		videoService.addVideo(video);
		
		//Create an updated video
		Video updatedVideo = new Video();
		updatedVideo.setGenre("Updated Genre");
		updatedVideo.setName("Updated Name");
		updatedVideo.setUrl("Updated URL");
		updatedVideo.setSize(11);
		
		//Update the video
		videoService.updateVideo(video.getId(), updatedVideo);
		
		//Check if the video was correctly updated
		Video videoFromService2 = videoService.getVideo(video.getId());
		assertEquals(videoFromService2.getName(),"Updated Name");
		assertEquals(videoFromService2.getSize(),new Integer(11));
		assertEquals(videoFromService2.getGenre(),"Updated Genre");
		assertEquals(videoFromService2.getUrl(),"Updated URL");
		
		
		
	}

}
