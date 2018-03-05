package com.topstar.volunteer.util;

import java.util.HashMap;
import java.util.Map;

public class MimeTypeUtils {
	
	public static Map<String,String> mimeType=new HashMap<String,String>();
    static{
        mimeType.put("rtf","text/rtf");
        mimeType.put("mif","application/vnd.mif");
        mimeType.put("ppt","application/vnd.ms-powerpoin");
        mimeType.put("gtar","application/x-gtar");
        mimeType.put("latex","application/x-latex");
        mimeType.put("aif","audio/x-aiff");
        mimeType.put("ief","image/ief");
        mimeType.put("pgm","image/x-portable-graymap");
        mimeType.put("iges","model/iges");
        mimeType.put("txt","text/plain");
        mimeType.put("movie","video/x-sgi-movie");
        mimeType.put("ez","application/andrew-inset");
        mimeType.put("smi","application/smil");
        mimeType.put("js","application/x-javascript");
        mimeType.put("nc","application/x-netcdf");
        mimeType.put("cdf","application/x-netcdf");
        mimeType.put("sv4crc","application/x-sv4crc");
        mimeType.put("tar","application/x-tar");
        mimeType.put("tcl","application/x-tclc");
        mimeType.put("roff","application/x-troff");
        mimeType.put("ustar","application/x-ustar");
        mimeType.put("zip","application/zip");
        mimeType.put("snd","audio/basic");
        mimeType.put("xyz","chemical/x-pdb");
        mimeType.put("jpg","image/jpeg");
        mimeType.put("png","image/png");
        mimeType.put("tiff","image/tiff");
        mimeType.put("tif","image/tiff");
        mimeType.put("ppm","image/x-portable-pixmap");
        mimeType.put("rgb","image/x-rgb");
        mimeType.put("xpm","model/vrml");
        mimeType.put("wrl","image/x-xpixmap");
        mimeType.put("css","text/css");
        mimeType.put("sgml","text/sgml");
        mimeType.put("etx","text/x-setext");
        mimeType.put("xwd","image/x-xwindowdump");
        mimeType.put("ice","x-conference/x-cooltalk");
        mimeType.put("htm","text/html");
        mimeType.put("doc","application/msword");
        mimeType.put("xls","application/vnd.ms-excel");
        mimeType.put("oda","application/oda");
        mimeType.put("pdf","application/pdf");
        mimeType.put("ai","application/postscript");
        mimeType.put("eps","application/postscript");
        mimeType.put("csh","application/x-csh");
        mimeType.put("dxr","application/x-director");
        mimeType.put("hdf","application/x-hdf");
        mimeType.put("skp","application/x-koan");
        mimeType.put("skd","application/x-koan");
        mimeType.put("skt","application/x-koan");
        mimeType.put("tr","application/x-troff");
        mimeType.put("me","application/x-troff-me");
        mimeType.put("src","application/x-wais-source");
        mimeType.put("au","audio/basic");
        mimeType.put("mid","audio/midi");
        mimeType.put("kar","audio/midi");
        mimeType.put("mp3","audio/mpeg");
        mimeType.put("aiff","audio/x-aiff");
        mimeType.put("wav","audio/x-wav");
        mimeType.put("pdb","chemical/x-pdb");
        mimeType.put("gif","image/gif");
        mimeType.put("jpeg","image/jpeg");
        mimeType.put("ras","image/x-cmu-raster");
        mimeType.put("rtx","text/richtext");
        mimeType.put("mpg","video/mpeg");
        mimeType.put("mov","video/quicktimef");
        mimeType.put("avi","video/x-msvideo");
        mimeType.put("hqx","application/mac-binhex40");
        mimeType.put("cpt","application/mac-compactpro");
        mimeType.put("ps","application/postscript");
        mimeType.put("smil","application/smil");
        mimeType.put("bcpio","application/x-bcpio");
        mimeType.put("vcd","application/x-cdlink");
        mimeType.put("dcr","application/x-director");
        mimeType.put("pgn","application/x-chess-pgn");
        mimeType.put("gz","application/zip");
        mimeType.put("tgz","application/zip");
        mimeType.put("pgn","application/x-chess-pgn");
        mimeType.put("midi","audio/midi");
        mimeType.put("mpga","audio/mpeg");
        mimeType.put("rm","audio/x-pn-realaudio");
        mimeType.put("ra","audio/x-realaudio");
        mimeType.put("jpe","image/jpeg");
        mimeType.put("asc","text/plain");
        mimeType.put("xml","text/xml");
        mimeType.put("html","text/html");
         
        mimeType.put("docx","application/msword");
        mimeType.put("pptx","application/vnd.ms-powerpoin");
        mimeType.put("xlsx","application/vnd.ms-excel");
         
        mimeType.put("mmap","application/vnd.mindjet.mindmanager");
        mimeType.put("mmp","application/vnd.mindjet.mindmanager");
        mimeType.put("mmpt","application/vnd.mindjet.mindmanager");
        mimeType.put("mmat","application/vnd.mindjet.mindmanager");
        mimeType.put("mmmp","application/vnd.mindjet.mindmanager");
        mimeType.put("mmas","application/vnd.mindjet.mindmanager");
        mimeType.put("xmind","application/xmind");
         
        mimeType.put("jar","application/java-archive");
    }
     
    public static String getMimeType(String fileName){
    	String fileExt=FileUtils.getFileSuffix(fileName);
        if(mimeType.containsKey(fileExt.toLowerCase())){
            return (String)mimeType.get(fileExt.toLowerCase());
        }else{
            return "application/octet-stream";
        }
    }

}
