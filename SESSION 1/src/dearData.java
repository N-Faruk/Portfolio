ArrayList<Adjustment> data = new ArrayList<Adjustment>(); 
int maxDays = 13; // change this whenever adding more days!

String[] types = {"Pin", "Smooth", "Rewrap", "Tighten", "Tuck"};
color[] typeColors = {color(140,110,170), color(230,150,130), color(120,150,130), color(100,140,180), color(220,200,120)};

void setup() {
  size(800, 500);
  noLoop();
  
  // Dataset
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
  addObservation(13, "Tuck", "Home");
}

void draw() {
  background(248, 249, 250);
  
  float chartX = 100, chartY = height - 100, chartWidth = width - 200, chartHeight = height - 200;
  float spacing = chartWidth / maxDays, barWidth = spacing * 0.7;
  
  // Baseline
  stroke(200); strokeWeight(2);
  line(chartX, chartY, chartX + chartWidth, chartY);
  
  // Render Stacked Bars
  for (int d = 1; d <= maxDays; d++) {
    float currentX = chartX + (d - 1) * spacing + (spacing - barWidth) / 2;
    float currentYOffset = 0;
    
    for (int i = 0; i < data.size(); i++) {
      Adjustment a = data.get(i);
      if (a.day == d) {
        float segmentHeight = chartHeight / 5.5;
        
        fill(getColorForType(a.type)); noStroke();
        rect(currentX, chartY - currentYOffset - segmentHeight, barWidth, segmentHeight - 3, 4);
        
        // Call short marker method
        drawPlaceMarker(a.place, currentX + barWidth / 2, chartY - currentYOffset - (segmentHeight / 2));
        
        currentYOffset += segmentHeight;
      }
    }
    fill(120); textAlign(CENTER, TOP); textSize(11);
    text("D" + d, currentX + barWidth / 2, chartY + 12);
  }
  
  // Legends (Types & Places)
  drawSimpleKey(chartX, 35, chartWidth);
}

// Custom method for big, clear visual location markers
void drawPlaceMarker(String placeName, float x, float y) {
  fill(255, 230); noStroke();
  rectMode(CENTER);
  
  if (placeName.equals("Home")) {
    ellipse(x, y, 10, 10); // Big solid dot
  } else if (placeName.equals("University")) {
    stroke(255, 230); strokeWeight(2); noFill();
    ellipse(x, y, 10, 10); // Big hollow circle
  } else if (placeName.equals("Outside")) {
    rect(x, y, 9, 9); // Big solid square
  }
  rectMode(CORNER); // reset back to default
}

// Combines both legends beautifully at the top
void drawSimpleKey(float x, float y, float w) {
  textSize(12); textAlign(LEFT, CENTER);
  
  // 1. Draw Type Colors
  float itemWidth = w / 5;
  for (int i = 0; i < types.length; i++) {
    fill(typeColors[i]); noStroke();
    ellipse(x + (i * itemWidth) + 5, y, 12, 12);
    fill(100); text(types[i], x + (i * itemWidth) + 22, y);
  }
  
  // 2. Draw Place Symbols Label Legend (One level lower)
  float placeY = y + 25;
  
  fill(150); noStroke(); ellipse(x + 5, placeY, 10, 10);
  fill(100); text("Home", x + 22, placeY);
  
  stroke(150); strokeWeight(2); noFill(); ellipse(x + 150, placeY, 10, 10);
  fill(100); noStroke(); text("University", x + 167, placeY);
  
  fill(150); rect(x + 300, placeY - 4, 9, 9);
  fill(100); text("Outside", x + 317, placeY);
}

void addObservation(int day, String type, String place) { data.add(new Adjustment(day, type, place)); }

color getColorForType(String typeStr) {
  for (int i = 0; i < types.length; i++) {
    if (types[i].equals(typeStr)) return typeColors[i];
  }
  return color(200);
}

class Adjustment {
  int day; String type, place;
  Adjustment(int d, String t, String p) { day = d; type = t; place = p; }
}
