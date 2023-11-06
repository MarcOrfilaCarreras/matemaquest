package cloud.marcorfilacarreras.matemaquest.redis;

import redis.clients.jedis.JedisPooled;

public class RedisController {
    private final static JedisPooled pool = new JedisPooled(System.getenv("REDIS_URL"), Integer.valueOf(System.getenv("REDIS_PORT")));
    
    public RedisController() {
    }
    
    /**
     * Retrieves an exam by its ID.
     *
     * @param id The ID of the exam.
     * @return The String containing the response.
     */
    public String getExam(int id){
        return pool.get("exam:" + id);
    }
    
    /**
    * Saves an exam.
    *
    * @param id The id of the exam.
    * @param json The JSON string of the exam.
    */
    public void saveExam(int id, String json){
        pool.set("exam:" + id, json);
    }
    
    /**
     * Retrieves a question by its ID.
     *
     * @param id The ID of the question.
     * @return The String containing the response.
     */
    public String getQuestion(int id){
        return pool.get("question:" + id);
    }
    
    /**
    * Saves a question.
    *
    * @param id The id of the question.
    * @param json The JSON string of the question.
    */
    public void saveQuestion(int id, String json){
        pool.set("question:" + id, json);
    }
}
