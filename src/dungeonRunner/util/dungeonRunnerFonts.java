package dungeonRunner.util;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;



public abstract class dungeonRunnerFonts {
	
	public static Font enemyFonttest = new Font("enemyFont", Font.PLAIN, 13);
	public static TrueTypeFont enemyFont = new TrueTypeFont(enemyFonttest,false);

	
	public static Font heroFonttest = new Font("heroFont", Font.BOLD, 16);
	public static TrueTypeFont heroFont = new TrueTypeFont(heroFonttest,false);
	
	public static Font heroBigtest = new Font("heroFont", Font.BOLD, 22);
	public static TrueTypeFont bigFont = new TrueTypeFont(heroBigtest,false);
	
	public static Font goldFonttest = new Font("goldFont", Font.PLAIN, 12);
	public static TrueTypeFont goldFont = new TrueTypeFont(goldFonttest,false);
}
