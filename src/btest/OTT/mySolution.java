package btest.OTT;
import java.util.*;

public class mySolution {
	private static final int MAX_GENRE = 5;
	private static final int MAX_MOVIES = 10000;
	private static final int MAX_USERS = 1000;
	
	static class Movie{
		int id, genre, totalScore;
		boolean isRemoved;
		HashSet<Integer> watchedUsers;
		
		Movie(int id, int genre, int totalScore){
			this.id = id;
			this.genre = genre;
			this.totalScore = totalScore;
			this.isRemoved = false;
			this.watchedUsers = new HashSet<>();
		}
	}
	
	static class MovieKey implements Comparable<MovieKey>{
		int total;
		int idx;
		
		MovieKey(int total, int idx){
			this.total = total;
			this.idx = idx;
		}
		
		public int compareTo(MovieKey o) {
			if(this.total != o.total)
				return Integer.compare(o.total, this.total);
			return Integer.compare(o.idx, this.idx);
		}
		
	}
	
	static class UserData{
		int idx; // 영화 인덱스
		int score; // 사용자 평점
		UserData(int idx, int score){
			this.idx = idx;
			this.score = score;
		}
	}
	
	
	
	private Movie[] movies = new Movie[MAX_MOVIES + 5];
	private TreeSet<MovieKey>[] genreToMoives = new TreeSet[MAX_GENRE + 5]; 
	private LinkedList<UserData>[] userToMovie = new LinkedList[MAX_USERS + 5];
	
	private HashMap<Integer, Integer> idToIdx = new HashMap<>();
	private HashMap<Integer, Integer> idxToId = new HashMap<>();
	
	private int idx;// 영화 내부 인덱스
	
	public void init(int N) {
		for(int g = 0 ; g <= MAX_GENRE; g++) {
			genreToMoives[g] = new TreeSet<>();
		}
		
		for(int u = 0; u <= MAX_USERS; u++) {
			userToMovie[u] = new LinkedList<>();
		}
		
		idToIdx.clear();
		idxToId.clear();
		idx = 0;
		Arrays.fill(movies, null);
	}
	
	public int add(int mID, int mGenre, int mTotal) {
		if(idToIdx.containsKey(mID))	return 0;
		
		idToIdx.put(mID, idx);
		idxToId.put(idx, mID); 
		
		Movie mv = new Movie(mID, mGenre, mTotal);
		
		movies[idx] = mv;
		
		MovieKey key = new MovieKey(mTotal, idx);
		genreToMoives[mGenre].add(key);
		genreToMoives[0].add(key);
		
		idx++;
		return 1;
	}
	
	public int erase(int mID) {
		Integer curIdx = idToIdx.get(mID);
		if(curIdx == null)	return 0;
		
		Movie mv = movies[curIdx];
		if(mv == null || mv.isRemoved) return 0;
		
		mv.isRemoved = true;
		
		return 1;
	}
	
	public int watch(int uID, int mID, int mRating) {
		Integer curIdx = idToIdx.get(mID);
		if(curIdx == null)	return 0;
		
		Movie mv = movies[curIdx];
		if(mv == null || mv.isRemoved)	return 0;
		
		if(mv.watchedUsers.contains(uID))	return 0;
		
		int curGenre = mv.genre;
		int curScore = mv.totalScore;
		
		userToMovie[uID].addFirst(new UserData(curIdx, mRating));
		
		MovieKey oldKey = new MovieKey(curScore, curIdx);
		genreToMoives[curGenre].remove(oldKey);
		genreToMoives[0].remove(oldKey);
		
		mv.totalScore += mRating;
		MovieKey newKey = new MovieKey(mv.totalScore, curIdx);
		genreToMoives[curGenre].add(newKey);
		genreToMoives[0].add(newKey);
		
		mv.watchedUsers.add(uID);
		return 1;
	}
	
	public Solution.RESULT suggest(int uID){
		Solution.RESULT res = new Solution.RESULT();
		
		int maxRating = 0;
		int maxGenre = 0;
		int cnt = 0;
		
		ListIterator<UserData> it = userToMovie[uID].listIterator();
		while(it.hasNext() && cnt < 5) {
			UserData entry = it.next();
			int tempIdx = entry.idx;
			int tempRating = entry.score;
			
			Movie mv = movies[tempIdx];
			
			if(mv == null || mv.isRemoved) {
				it.remove();
				continue;
			}else {
				if(maxRating < tempRating) {
					maxRating = tempRating;
					maxGenre = mv.genre;
				}
				cnt++;
			}
			
		}
		res.cnt = 0;
		for(MovieKey key : genreToMoives[maxGenre]) {
			int tempIdx = key.idx;
			Movie mv = movies[tempIdx];
			if(mv == null || mv.isRemoved)	continue;
			
			if(mv.watchedUsers.contains(uID))	continue;
			
			Integer tempID = idxToId.get(tempIdx);
			if(tempID == null)	continue;
			
			
			res.IDs[res.cnt++] = tempID;
			if(res.cnt >=5 ) break;
		}
		
		
		return res;
		
	}
	
}
