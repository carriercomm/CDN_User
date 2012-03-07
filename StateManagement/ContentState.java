package StateManagement;

import java.util.BitSet;
import java.util.List;

public class ContentState{
	
	public enum Type{tcpUpload,tcpDownload,dtn}
	
	private String contentId;
	private int offset;
	private int preferredInterface;
	private List<String> preferredRoute;
	private int totalSegments;
	public int currentSegments;
	private Type stateType;
	private String AppId;
	private String uploadId;
	private boolean sendMetaDataFlag;
	public BitSet bitMap ;

	
	public ContentState(String content,int off,BitSet bits,int conInterface,List<String> route,int size,int curSeg,Type type,String appId,boolean sendMetaData)
	{
		contentId = content;
		offset = off;
		bitMap = bits ;
		preferredInterface = conInterface;
		preferredRoute = route;
		totalSegments = size;
		currentSegments = curSeg ;
		stateType = type;
		AppId = appId;
		sendMetaDataFlag = sendMetaData;
	}

	public ContentState(String contentName,String uploadName,int off,BitSet bits, int conInterface,List<String> route,int size,int curSeg,Type type,String appId,boolean sendMetaData)
	{
		if((type == Type.tcpUpload) || (type == Type.dtn))
		{
			contentId = contentName;
			uploadId = uploadName;
			offset = off;
			bitMap = bits ;
			preferredInterface = conInterface;
			preferredRoute = route;
			totalSegments = size;
			currentSegments = curSeg ;
			stateType = type;
			AppId = appId;
			sendMetaDataFlag = sendMetaData;

		}
	}
	
	public boolean getMetaDataFlag()
	{
		return sendMetaDataFlag;
	}
	public String getUploadId()
	{
		return uploadId;
	}
	
	public String getAppId()
	{
		return AppId;
	}
	
	public String getContentId()
	{
		return contentId;
	}
	
	public int getPreferredInterface()
	{
		return preferredInterface;
	}
	public List<String> getPreferredRoute()
	{
		return preferredRoute;
	}
	public int getTotalSegments()
	{
		return totalSegments;
	}
	public int getCurrentSegments()
	{
		return currentSegments;
	}
	public Type getStateType()
	{
		return stateType;
	}
	public int getOffset()
	{
		return offset;
	}
	
	public BitSet getBitMap(){
		return bitMap;
	}
	
}
