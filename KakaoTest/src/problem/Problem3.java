///**
// * @ClassName : Problem3
// * @Author : Kim HyunYong
// * @Date : 2017. 9. 16.
// * @Description : -
// * @Version : 1.0
// * @History : 수정일, 수정자, 수정내용
// */
//
//package problem;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Problem3 {
//	public static void main(String[] args) {
//		
//	}
//
//	public static int solution(int cacheSize, String[] cities) {
//		int answer = 0;
//		
//		Map cache = new LinkedHashMap(MAX_ENTRIES+1, .75F, true) {
//		    // This method is called just after a new entry has been added
//		    public boolean removeEldestEntry(Map.Entry eldest) {
//		        return size() > MAX_ENTRIES;
//		    }
//		};
//		
//		
//		return answer;
//	}
//	
//	// http://sjava.net/2011/03/%EC%9E%90%EB%B0%94java%EC%97%90%EC%84%9C%EC%9D%98-lrucache-%EB%A9%94%EB%AA%A8%EB%A6%AC%EB%A6%AD%EA%B3%BC-%ED%95%B4%EA%B2%B0%EB%B0%A9%EC%95%88/
//	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//	    boolean isRemove = size() > maxsize;
//	 
//	    if (isRemove) {
//	        Object obj = this.get(eldest.getKey());  
//	        if(obj instanceof BufferedWriter) { // V가 BufferedWriter 인스턴스임.. 
//	            try {
//	                ((BufferedWriter)obj).close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        this.remove(eldest.getKey());
//	    }
//	 
//	    return isRemove;
//	}
//}
