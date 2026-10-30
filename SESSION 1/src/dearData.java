//THE LITTLE ADJUSTMENTS - Dear Data 100

ArrayList<Adjustment> data = new ArrayList<Adjustment>();

void setup() {
  size(800, 800);
  noLoop(); 
  

  addObservation(1, "Pin", "Home");
  addObservation(1, "Smooth", "Outside"); 
  addObservation(1, "Pin", "University"); 
  addObservation(2, "Rewrap", "Home");
  addObservation(2, "Tighten", "Outside");
  addObservation(3, "Smooth", "Home");
  addObservation(4, "Tighten", "Home");
  addObservation(4, "Rewrap", "University");
  addObservation(4, "Tuck", "Outside"); 
  addObservation(4, "Smooth", "Home");
  addObservation(5, "Tighten", "Home");
  addObservation(5, "Rewrap", "University");
  addObservation(5, "Tuck", "Outside"); 
  addObservation(5, "Smooth", "Home");
  addObservation(6, "Tighten", "Home");
  addObservation(6, "Rewrap", "University");
  addObservation(6, "Tuck", "Outside"); 
  addObservation(6, "Smooth", "Home");
  addObservation(7, "Rewrap", "Home");
  addObservation(7, "Tighten", "Outside");
  addObservation(8, "Smooth", "Home");
  addObservation(9, "Tighten", "Home");
  addObservation(9, "Rewrap", "University");
  addObservation(9, "Tuck", "Outside");
  addObservation(9, "Smooth", "Home");
  addObservation(10, "Tighten", "Home");
  addObservation(10, "Rewrap", "University");
  addObservation(10, "Tuck", "Outside");
  addObservation(10, "Smooth", "Home");
  addObservation(11, "Rewrap", "Home");
  addObservation(11, "Tighten", "Outside");
  addObservation(12, "Smooth", "Home");

}

void draw() {
  background(248, 245, 240); 
  
  randomSeed(100); 
  
  for (int i = 0; i < data.size(); i++) {
    Adjustment a = data.get(i);
    
    // styling based on the adjustment type
    if (a.type.equals("Pin")) {
      fill(140, 110, 170, 40); // Translucent Purple
      stroke(140, 110, 170, 180);
    } else if (a.type.equals("Smooth")) {
      fill(230, 150, 130, 40); // Warm Coral
      stroke(230, 150, 130, 180);
    } else if (a.type.equals("Rewrap")) {
      fill(120, 150, 130, 40); // Sage Green
      stroke(120, 150, 130, 180);
    } else if (a.type.equals("Tighten")) {
      fill(100, 140, 180, 40); // Soft Blue
      stroke(100, 140, 180, 180);
    } else {
      fill(220, 200, 120, 40); //  Mustard Yellow (for any other type)
      stroke(220, 200, 120, 180);
    }

    // METHOD USE: Get the number of total adjustments recorded for this specific day
    int dailyCount = countAdjustmentsForDay(a.day);
    
    // fabric stroke thickness reflect how busy that day was!
    strokeWeight(1.0 + (dailyCount * 1.5));
    
    // organic fabric shapes
    beginShape();
    float startY = random(100, 700);
    float endY = random(100, 700);
    
    vertex(0, startY);
    bezierVertex(width * 0.3, startY - 150, width * 0.7, endY + 150, width, endY);
    vertex(width, endY + random(50, 150)); 
    bezierVertex(width * 0.7, endY + 250, width * 0.3, startY + 50, 0, startY + 100);
    endShape(CLOSE);
    
    if (a.type.equals("Pin")) {
      fill(40);
      noStroke();
      ellipse(width * 0.3, startY - 20, 8, 8);
    }
  }
}

//  method to accept the day parameter
void addObservation(int day, String type, String place) {
  data.add(new Adjustment(day, type, place));
}

// METHOD: Counts all adjustments made on a specific day number
int countAdjustmentsForDay(int dayNum) {
  int total = 0;
  for (int i = 0; i < data.size(); i++) {
    Adjustment current = data.get(i);
    if (current.day == dayNum) {
      total++;
    }
  }
  return total;
}

// Custom data container class
class Adjustment {
  int day; // Storing the day number inside the object
  String type, place;
  
  Adjustment(int d, String t, String p) {
    day = d;
    type = t;
    place = p;
  }
}
