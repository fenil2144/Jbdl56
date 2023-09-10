package com.example.redisDemo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event.ID;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PersonController {
	
	private static final String PERSON_KEY_PREFIX = "per::";
	private static final String PERSON_LIST_KEY_PREFIX = "per_list::";
	private static final String PERSON_HASH_KEY_PREFIX = "per_hash::";

	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping("/person")
	public void savePerson(@RequestBody Person person) {
		
		String key = getKey(person.getId());
		redisTemplate.opsForValue().set(key, person);
		redisTemplate.expire(key, 10, TimeUnit.SECONDS);

	}
	
	private String getKey(long id) {
		return PERSON_KEY_PREFIX+id;
	}
	
	@GetMapping("/person")
	public Person getPerson(@RequestParam("id") long id) {
		
		String key = getKey(id);
		return (Person) redisTemplate.opsForValue().get(key);
	}
	
	@PostMapping("/person/lpush")
	public void lpush(@RequestBody List<Person> personList) {
		redisTemplate.opsForList().leftPushAll(PERSON_LIST_KEY_PREFIX, personList);
	}
	
	@PostMapping("/person/rpush")
	public void rpush(@RequestBody List<Person> personList) {
		redisTemplate.opsForList().rightPushAll(PERSON_LIST_KEY_PREFIX, personList);
	}
	
	@DeleteMapping("/person/lpop")
	public List<Person> lpop() {
		return (List<Person>) redisTemplate.opsForList().leftPop(PERSON_LIST_KEY_PREFIX);
	}
	
	@DeleteMapping("/person/rpop")
	public List<Person> rpop(@RequestParam(value="count",required = false,defaultValue = "1") int count) {
		
		return redisTemplate.opsForList().rightPop(PERSON_LIST_KEY_PREFIX,count).stream()
				.map(x -> (Person)x).collect(Collectors.toList());
	}
	
	@GetMapping("/person/lrange")
	public List<Person> lrange(@RequestParam(value="start",required = false,defaultValue = "0") int start,
			@RequestParam(value="end",required = false,defaultValue = "-1") int end){
		return redisTemplate.opsForList().range(PERSON_LIST_KEY_PREFIX,start,end).stream()
				.map(x -> (Person)x).collect(Collectors.toList());
	}
	
	//Hash Operation
	private String getHashKey(long id) {
		return PERSON_HASH_KEY_PREFIX+id;
	}
	
	@PostMapping("/person/hash")
	public void savePersonInHash(@RequestBody List<Person> persons) {
		
		persons.stream().forEach(person -> {
			Map map = objectMapper.convertValue(person, Map.class);
			redisTemplate.opsForHash().putAll(getHashKey(person.getId()), map);

		});
	}
	
	@GetMapping("/person/hash")
	public List<Person> getPersons(@RequestParam("ids") List<Long> ids){
		return ids.stream().map(i -> redisTemplate.opsForHash().entries(getHashKey(i)))
				.map(entryMap -> objectMapper.convertValue(entryMap, Person.class))
				.collect(Collectors.toList());
		
	}

}
