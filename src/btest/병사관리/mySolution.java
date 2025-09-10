package btest.병사관리;

class mySolution
{
	public class Node{
		int id; // ID
		int v; // 버젼관리
		Node next;
		
		Node(){}
		Node(int id, int v){this.id = id; this.v = v; this.next = null;}
		Node(int id, int v, Node next){this.id = id; this.v = v; this.next = next;}
	}
	
	public class Team{
		Node[] head = new Node[6];
		Node[] tail = new Node[6];
	}
	
	public Node[] node = new Node[200055];
	public int cnt = 0;
	public int[] version = new int[100005];
	public int[] teamNum = new int[100005];
	
	public Node getNewNode(int id, Node next) {
		Node ret = node[cnt++];
		ret.id = id;
		ret.v = ++version[id];
		ret.next = next;
		
		return ret;
	}
	
	public Team[] teams = new Team[6];
	
	public void init()
	{
		cnt = 0;
		for(int i = 0 ; i < 200055; i++) {
			if(node[i] == null)
				node[i] = new Node();
		}
		
		for(int i = 1; i <= 5; i++) {
			teams[i] = new Team();
			for(int j = 1; j <= 5; j++) {
				teams[i].head[j] = teams[i].tail[j] = getNewNode(0, null);
			}
		}
		
		for(int i = 0 ; i <= 100000; i++) {
			version[i] = 0;
			teamNum[i] = 0; 
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Node newNode = getNewNode(mID, null);
		teams[mTeam].tail[mScore].next = newNode;
		teams[mTeam].tail[mScore]  = newNode;
		teamNum[mID] = mTeam;
	}
	
	public void fire(int mID)
	{
		version[mID] = -1;
	}

	public void updateSoldier(int mID, int mScore)
	{
		hire(mID, teamNum[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		if(mChangeScore < 0) {
			for(int before = 1; before <= 5; before++) {
				int after = before + mChangeScore;
				if(after >=5)	after = 5;
				else if(after <= 1)	after = 1;
				if(before == after)	continue;
				
				if(teams[mTeam].head[before].next == null)
					continue;
				teams[mTeam].tail[after].next = teams[mTeam].head[before].next;
				teams[mTeam].tail[after] = teams[mTeam].tail[before];
				teams[mTeam].head[before].next = null;
				teams[mTeam].tail[before] = teams[mTeam].head[before];
				
			}
		}
		
		if(mChangeScore > 0) {
			for(int before = 5; before >= 1; before--) {
				int after = before + mChangeScore;
				if(after >=5)	after = 5;
				else if(after <= 1)	after = 1;
				if(before == after)	continue;
				
				if(teams[mTeam].head[before].next == null)
					continue;
				teams[mTeam].tail[after].next = teams[mTeam].head[before].next;
				teams[mTeam].tail[after] = teams[mTeam].tail[before];
				teams[mTeam].head[before].next = null;
				teams[mTeam].tail[before] = teams[mTeam].head[before];
			}
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		for(int i = 5; i >= 1; i--) {
			Node node = teams[mTeam].head[i].next;
			if(node == null)	continue;
			
			int ans = 0;
			while(node != null) {
				if(node.v == version[node.id])
					ans = ans < node.id ? node.id : ans;
				node = node.next;
			}
			
			if(ans !=0)	return ans;
		}
		
		return 0;
	}
}