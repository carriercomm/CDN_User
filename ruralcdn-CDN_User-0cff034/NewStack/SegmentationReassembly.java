package NewStack;

import java.util.concurrent.BlockingQueue;

import StateManagement.StateManager;

import prototype.datastore.DataStore;

public class SegmentationReassembly{

	private Scheduler scheduler;
	private DataStore store;
	private DataStore dbStore ;
	private int segmentSize;
	private int dtnSize ;
	private int logSize ;
	Reassembler reassembler;
	Segmenter segmenter;
	public long globalsegment ;
	public long globaldtnsegment ;
	public long segment;
	public static final String metadataSuffix = new String(".metadata");
	
	public SegmentationReassembly(StateManager manager,DataStore st,DataStore dst,Scheduler sched,int segmentsize,int dtnsize,int logSegmentSize, BlockingQueue<String> downloads)
	{
		store = st;
		dbStore = dst ;
		scheduler = sched;
		segmentSize = segmentsize;
		dtnSize = dtnsize ;
		logSize = logSegmentSize ;
		reassembler = new Reassembler(scheduler.getDataInQueue(),store,dbStore,manager,segmentSize,logSize,downloads);
		reassembler.start();
		segmenter = new Segmenter(store,dbStore,segmentSize,dtnSize,logSize,scheduler.getDataFullQueues());
		System.out.println("Inside NewStack.SegmentationReassembly: Method SegmentationReassembly");
	}
	public SegmentationReassembly() {
		// TODO Auto-generated constructor stub
	}
	public long countSegments(String dataname)
	{
		System.out.println("Inside NewStack.SegmentationReassembly: Method countSegments");
		DataStore store ;
		int size = segmentSize;
		store = this.store;
		if(dataname.contains(".log") || dataname.contains(".jpg")){
			size = logSize ;
			store = dbStore ;
			
		}	
		long smallchunk = (store.length(dataname)%size);
		if(smallchunk == 0){
			globalsegment = (store.length(dataname)/size);
			return globalsegment;
		}
		else{
			globalsegment = (((store.length(dataname) - smallchunk)/size) + 1);
			return globalsegment;
		}		
			
	}
	
	public long countDtnSegments(String dataname)
	{
		System.out.println("Inside NewStack.SegmentationReassembly: Method countDtnSegments");
		long smallchunk = (store.length(dataname)%dtnSize);
		if(smallchunk == 0){
			globaldtnsegment = (store.length(dataname)/dtnSize);
			System.err.println("globaldtnsegment"+globaldtnsegment);
			return globaldtnsegment;
		}	
		else{
			globaldtnsegment = (((store.length(dataname) - smallchunk)/dtnSize) + 1);
			return globaldtnsegment;
		}
	}
	public void close()
	{
		reassembler.close();
	}
	public Segmenter getSegmenter()
	{
		return segmenter;
	}

}