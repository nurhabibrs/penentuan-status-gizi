package giziStatus;

public class Fuzzy {
	public static float BBringan;
	public static float BBnormal;
	public static float BBberat;
	public static float Trendah;
	public static float Tnormal;
	public static float Ttinggi;
	public static float a_predikat1, a_predikat2, a_predikat3, a_predikat4, a_predikat5, a_predikat6, a_predikat7, a_predikat8, a_predikat9;
	public static float z1, z2, z3, z4, z5, z6, z7, z8, z9;
	public static float a_pred_z, a_pred, z;
	public static String nilai_gizi;
	public static String rules[];
	
	public static Float fuzzifikasiBB(float BeratBadan) {
		if (BeratBadan <= 40) {
			BBringan = 1;
			BBnormal = 0;
			BBberat = 0;
		} else if (BeratBadan > 40 && BeratBadan <= 55 ) {
			if(BeratBadan > 45) {
				BBnormal = (BeratBadan - 45)/10;
				BBringan = (55 - BeratBadan)/15;
				BBberat = 0;
			} else if (BeratBadan <= 45) {
				BBnormal = 0;
				BBringan = (55 - BeratBadan)/20;
				BBberat = 0;
			} 
		} else if (BeratBadan > 55 && BeratBadan <= 75) {
			if (BeratBadan <= 65) {
				BBringan = 0;
				BBnormal = (65 - BeratBadan)/10;
				BBberat = (BeratBadan - 55)/20;
			} else {
				BBringan = 0;
				BBnormal = 0;
				BBberat = (BeratBadan - 55)/20;
			}
		} else if (BeratBadan > 75) {
			BBringan = 0;
			BBnormal = 0;
			BBberat = 1;
		}
//		System.out.println("Bb: "+BBringan);
//		System.out.println("Bbn: "+BBnormal);
//		System.out.println("BbB: "+BBberat);
		return BeratBadan;
	}
	
	public static Float fuzzifikasiTinggi(float TinggiBadan) {
		if (TinggiBadan <= 150 ) {
			Trendah = 1;
			Tnormal = 0;
			Ttinggi = 0;
		} else if (TinggiBadan > 150 && TinggiBadan <= 165) {
			if (TinggiBadan > 155) {
				Trendah = (165 - TinggiBadan)/15;
				Tnormal = (TinggiBadan - 155)/10;
				Ttinggi = 0;
			} else if (TinggiBadan <= 155) {
				Trendah = (165- TinggiBadan)/25;
				Tnormal = 0;
				Ttinggi = 0;
			}
		} else if (TinggiBadan > 165 && TinggiBadan <= 185 ) {
			if (TinggiBadan <= 175) {
				Trendah = 0;
				Tnormal = (175 - TinggiBadan)/10;
				Ttinggi = (TinggiBadan - 165)/20;
			} else {
				Trendah = 0;
				Tnormal = 0;
				Ttinggi = (TinggiBadan - 165)/20;
			}
		} else if (TinggiBadan > 185) {
			Trendah = 0;
			Tnormal = 0;
			Ttinggi = 1;
		}
//		System.out.println("Tinggi : "+Ttinggi);
//		System.out.println("Tinggin : "+Tnormal);
//		System.out.println("Tinggit : "+Ttinggi);
		return TinggiBadan;
	}
	
	public static void fuzzifikasi(float BB, float TB) {
		fuzzifikasiBB(BB);
		fuzzifikasiTinggi(TB);
		//		z = zmax - a_predikat(zmax - zmin) ; 
	//		[1] Jika berat badan = ringan dan tinggi badan = rendah ==> normal (1)
			a_predikat1 = Math.min(BBringan, Trendah);
			z1 = (float) (25 - a_predikat1*(25 - 17.5));
	//		[2] Jika berat badan = ringan dan tinggi badan = normal ==> kurus tingkat ringan (2)
			a_predikat2 = Math.min(BBringan, Tnormal);
			z2 = (float) (18.5 - a_predikat2*(18.5 - 16));
	//		[3] Jika berat badan  = ringan dan tinggi badan = tinggi  ==> kurus tingkat berat (3)
			a_predikat3 = Math.min(BBringan, Ttinggi);
			z3 = (float) 17 - a_predikat3*(17 - 13);
	//		[4] Jika berat badan = normal dan tinggi badan = rendah ==> gemuk tingkat ringan (4)
			a_predikat4 = Math.min(BBnormal, Trendah);
			z4 = (float) 27 - a_predikat4*(27 - 24);
	//		[5] Jika berat badan = normal dan tinggi badan = normal ==> normal (5)
			a_predikat5 = Math.min(BBnormal, Tnormal);
			z5 = (float) (25 - a_predikat5*(25 - 17.5));
	//		[6] Jika berat badan = normal dan tinggi badan = tinggi ==> kurus tingkat ringan (6)
			a_predikat6 = Math.min(BBnormal, Ttinggi);
			z6 = (float) (18.5 - a_predikat6*(18.5 - 16));
	//		[7] Jika berat badan = berat dan tinggi badan = rendah ==> gemuk tingkat berat (7)
			a_predikat7 = Math.min(BBberat, Trendah);
			z7 = (float) 33 - a_predikat7*(33 - 26);
	//		[8] Jika berat badan = berat dan tinggi badan = normal ==> gemuk tingkat ringan (8)
			a_predikat8 = Math.min(BBberat, Tnormal);
			z8 = (float) 27 - a_predikat8*(27 - 24);
	//		[9] Jika berat badan = berat dan tinggi badan = tinggi ==> normal (9)
			a_predikat9 = Math.min(BBberat, Ttinggi);
			z9 = (float) (25 - a_predikat9*(25 - 17.5));
//			System.out.println("a1 : "+a_predikat1+" z1 : "+z1);
//			System.out.println("a2 : "+a_predikat2+" z2 : "+z2);
//			System.out.println("a3 : "+a_predikat3+" z3 : "+z3);
//			System.out.println("a4 : "+a_predikat4+" z4 : "+z4);
//			System.out.println("a5 : "+a_predikat5+" z5 : "+z5);
//			System.out.println("a6 : "+a_predikat6+" z6 : "+z6);
//			System.out.println("a7 : "+a_predikat7+" z7 : "+z7);
//			System.out.println("a8 : "+a_predikat8+" z8 : "+z8);
//			System.out.println("a9 : "+a_predikat9+" z9 : "+z9);
	}
	
	public static float defuzzifikasi(float BB, float TB) {
		fuzzifikasi(BB, TB);
		a_pred_z = (a_predikat1*z1)+(a_predikat2*z2)+(a_predikat3*z3)+(a_predikat4*z4)+(a_predikat5*z5)+(a_predikat6*z6)+(a_predikat7*z7)+(a_predikat8*z8)+(a_predikat9*z9);
		a_pred = a_predikat1 + a_predikat2 + a_predikat3 + a_predikat4 + a_predikat5 + a_predikat6 + a_predikat7 + a_predikat8 + a_predikat9;
		z = a_pred_z/a_pred;
//		System.out.println(z);
		return z;
	}
	
	public static String status(float z) {
		if (a_predikat1 == 1 || a_predikat5 == 1 || a_predikat9 == 1) {
			nilai_gizi = "NORMAL";
		} else if ( a_predikat2 == 1 || a_predikat6 == 1) {
			nilai_gizi = "KURUS TINGKAT RINGAN";
		} else if (a_predikat3 == 1) {
			nilai_gizi = "KURUS TINGKAT BERAT";
		} else if (a_predikat4 == 1 || a_predikat8 == 1) {
			nilai_gizi = "GEMUK TINGKAT RINGAN";
		} else if (a_predikat7 == 1) {
			nilai_gizi = "GEMUK TINGKAT BERAT";
		} else 
			if (z >= 13 && z <= 16) {
			nilai_gizi = "KURUS TINGKAT BERAT";
		} else if (z > 16 && z <= 18.5) {
			nilai_gizi = "KURUS TINGKAT RINGAN";
		} else if (z > 18.5 && z <= 24) {
			nilai_gizi = "NORMAL";
		} else if (z > 24 && z <= 26) {
			nilai_gizi = "GEMUK TINGKAT RINGAN";
		} else if (z > 26 && z <= 33) {
			nilai_gizi = "GEMUK TINGKAT BERAT";
		} else {
			nilai_gizi = "Tidak Terdefinisi";
		}
		return nilai_gizi;
	}
	
	public static String[] rules(String rule[]) {
		String a1 = String.valueOf(a_predikat1);
		rule[0] = a1+"Jika berat badan = ringan dan tinggi badan = rendah ==> normal";
//		rule[1] = a_predikat2+"Jika berat badan = ringan dan tinggi badan = rendah ==> normal";
		System.out.println(rule[0]);
		return rule;
	}
}
