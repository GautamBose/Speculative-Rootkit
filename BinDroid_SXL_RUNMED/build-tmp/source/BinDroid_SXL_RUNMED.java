import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BinDroid_SXL_RUNMED extends PApplet {

PImage bgref;
int BUTTON_HEIGHT;
basicButton apply;
PFont segoe;

public void setup() {
    
    // pixelDensity(displayDensity());
    background(0);
    bgref = loadImage("Screenshot_3.png");
    apply = new basicButton(0, 0, 128, "Apply");
    BUTTON_HEIGHT = 40;
    segoe = createFont("SegoeUI", 23);
}

abstract class button {
    int x, y, w, h;
}

class basicButton extends button {
    int x, y, w, h;

    int fillColor;
    int borderColor;

    int highlightedFillColor;
    int highlightedBorderColor;

    int borderWidth;
    //@TODO build out this highlighting system
    boolean isHighlighted;

    String buttonText;

    basicButton(int x, int y, int w, String buttonText) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = 40;
        isHighlighted = false;
        fillColor = color(225, 225, 225, 100);
        borderColor = color(176, 176, 176, 100);

        this.buttonText = buttonText;

        highlightedFillColor = color(229, 241, 251);
        highlightedBorderColor = color(0, 114, 207);
    }

    public void renderText() {
        fill(0);
        textFont(segoe);
        textAlign(CENTER, BOTTOM);
        text(this.buttonText, (x+w)/2, h - 8);
    }

    public void render() {
        noStroke();
        if (!isHighlighted) {
            fill(this.borderColor);
            rect(x, y, w, h);
            fill(this.fillColor);
            rect(x + 2, y + 2, w - 4, h - 4);
        } else {
            fill(this.highlightedBorderColor);
            rect(x, y, w, h);
            fill(this.highlightedFillColor);
            rect(x + 2, y + 2, w - 4, h - 4);
        }

        this.renderText();
    }


    public boolean isOver(float mx, float my) {
        if (x <= mx && mx <= (x + w) && y <= my && my <= (y + h)) {
            this.isHighlighted = true;
            return true;
        }
        else {
            this.isHighlighted = false;
            return false;
        }
    }

    public void onClickListner(float mx, float my) {
        // here we call the buttons listner. Lets do observer pattern.
    }

}

public void draw() {
    image(bgref, 0, 0);
    apply.render();
}

public void mouseMoved() {
    apply.isOver(mouseX, mouseY);

}
  public void settings() {  size(466, 40); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "BinDroid_SXL_RUNMED" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
