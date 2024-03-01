import java.util.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AuditDegree {
    private String url; 
    private Set<String> classes;
    private List<String> majors;

    public AuditDegree(String url) {
        this.url = url;
        this.majors = new ArrayList<>(Arrays.asList("AMATH", "MATH/STAT", "MATH", "CSE", "INFO", "BIOST/STAT", "STAT", 
                "BIOST", "BIO", "CHEM", "PHYS", "HCDE", "ENGL", "NURS", "NCLIN", "NMETH", "PHIL", "QMETH", "Q SCI", "EDPSY"));
        classes = classHelper();
    }

    public void printClasses() {
        String ans = "(";
        for (String course : classes) {
            if(course.length() > 1){
                ans += course + ", ";
            }
        }
        ans = ans.substring(0, ans.length() - 2) + ")";
        System.out.println(ans);
    }

    public Set<String> commonClasses(AuditDegree other) {
        Set<String> common = new TreeSet<>();
        for(String class1 : classes){
            if(other.getClasses().contains(class1)){
                common.add(class1);
            }
        }
        return common;
    }

    public Set<String> getClasses(){
        return new HashSet<>(classes);
    }
    
    private Set<String> classHelper() {
        Set<String> allClasses = new TreeSet<>();
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
            //System.out.println(doc);
            Elements temp = doc.select("li");
            
            for (Element courseName : temp) {
                //Element spanElement = courseName.selectFirst("span.linkified");
                String allText = courseName.text();
                //System.out.println(allText);
                String course = "";
                for(int i = 0; i < majors.size(); i++){
                    while(allText.contains(majors.get(i))) {
                        String t2 = allText;
                        int currIdx = allText.indexOf(majors.get(i));
                        course = allText.substring(currIdx, currIdx + majors.get(i).length() + 4);
                        allText = t2.substring(0, currIdx) + t2.substring(currIdx + majors.get(i).length() + 4);
                        allClasses.add(course);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allClasses;
    }
}

// edge case: phys 121, 122, 123, some classes are the same and have different names