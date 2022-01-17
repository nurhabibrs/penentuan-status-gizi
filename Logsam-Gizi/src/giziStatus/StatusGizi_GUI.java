package giziStatus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class StatusGizi_GUI {

	private JFrame frame;
	private JTextField BeratBadan;
	private JTextField TinggiBadan;
	private JTextField nilaiGizi;
	private JTextField statusGizi;
	private JTextField a_beratBadan;
	private JTextField a_tinggiBadan;
	private JTextArea rule;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusGizi_GUI window = new StatusGizi_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatusGizi_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 529, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFuzzyInferenceSystem = new JLabel("STATUS GIZI REMAJA");
		lblFuzzyInferenceSystem.setForeground(SystemColor.desktop);
		lblFuzzyInferenceSystem.setBackground(new Color(0, 0, 0));
		lblFuzzyInferenceSystem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblFuzzyInferenceSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuzzyInferenceSystem.setBounds(0, 51, 519, 28);
		frame.getContentPane().add(lblFuzzyInferenceSystem);
		
		JLabel lblNewLabel = new JLabel("Input");
		lblNewLabel.setBackground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(106, 103, 58, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBeratBadan = new JLabel("Berat Badan");
		lblBeratBadan.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblBeratBadan.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeratBadan.setBounds(24, 163, 63, 14);
		frame.getContentPane().add(lblBeratBadan);
		
		JLabel lblTinggiBadan = new JLabel("Tinggi Badan");
		lblTinggiBadan.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblTinggiBadan.setBounds(24, 188, 72, 21);
		frame.getContentPane().add(lblTinggiBadan);
		
		BeratBadan = new JTextField();
		BeratBadan.setBounds(106, 161, 48, 20);
		frame.getContentPane().add(BeratBadan);
		BeratBadan.setColumns(10);
		
		TinggiBadan = new JTextField();
		TinggiBadan.setBounds(106, 189, 48, 20);
		frame.getContentPane().add(TinggiBadan);
		TinggiBadan.setColumns(10);
		
		JLabel lblKg = new JLabel("(35 - 80)kg");
		lblKg.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblKg.setBounds(164, 156, 74, 28);
		frame.getContentPane().add(lblKg);
		
		JLabel lblCm = new JLabel("(145 - 190)cm");
		lblCm.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblCm.setBounds(164, 191, 79, 14);
		frame.getContentPane().add(lblCm);
		
		JLabel lblFuzzyInferenceSystem_1 = new JLabel("FUZZY INFERENCE SYSTEM");
		lblFuzzyInferenceSystem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuzzyInferenceSystem_1.setForeground(SystemColor.desktop);
		lblFuzzyInferenceSystem_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		lblFuzzyInferenceSystem_1.setBackground(Color.BLACK);
		lblFuzzyInferenceSystem_1.setBounds(0, 0, 519, 59);
		frame.getContentPane().add(lblFuzzyInferenceSystem_1);
		
		JButton btnAnalisis = new JButton("Analisis");
		btnAnalisis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Float BBadan, TBadan, z, fuzBB, fuzTB;
				String status;
				BBadan = Float.valueOf(BeratBadan.getText());
				TBadan = Float.valueOf(TinggiBadan.getText());
				z = Fuzzy.defuzzifikasi(BBadan, TBadan);
				fuzBB = Fuzzy.fuzzifikasiBB(BBadan);
				fuzTB = Fuzzy.fuzzifikasiTinggi(TBadan);
				status = Fuzzy.status(z);
				nilaiGizi.setText(String.valueOf(z));
				a_beratBadan.setText(String.valueOf(fuzBB));
				a_tinggiBadan.setText(String.valueOf(fuzTB));
				statusGizi.setText(status);
				if (Fuzzy.a_predikat3 == 1) {
					statusGizi.setBackground(Color.RED);
				} else if (Fuzzy.a_predikat2 == 1 || Fuzzy.a_predikat6 == 1) {
					statusGizi.setBackground(Color.YELLOW);
				} else if (Fuzzy.a_predikat1 == 1 || Fuzzy.a_predikat5 == 1 || Fuzzy.a_predikat9 == 1) {
					statusGizi.setBackground(Color.GREEN);
				} else if (Fuzzy.a_predikat4 == 1 || Fuzzy.a_predikat8 == 1) {
					statusGizi.setBackground(Color.YELLOW);
				} else if (Fuzzy.a_predikat7 == 1) {
					statusGizi.setBackground(Color.RED);
				} else if (Fuzzy.z >= 13 && Fuzzy.z <= 16) {
					statusGizi.setBackground(Color.RED);
				} else if (Fuzzy.z > 16 && Fuzzy.z <= 18.5) {
					statusGizi.setBackground(Color.YELLOW);
				} else if (Fuzzy.z > 18.5 && Fuzzy.z <= 24) {
					statusGizi.setBackground(Color.GREEN);
				} else if (Fuzzy.z > 24 && Fuzzy.z <= 26) {
					statusGizi.setBackground(Color.YELLOW);
				} else if (Fuzzy.z > 26 && Fuzzy.z <= 33) {
					statusGizi.setBackground(Color.RED);
				} else {
					statusGizi.setBackground(Color.GRAY);
				}
				String a1 = String.valueOf(Fuzzy.a_predikat1);
				String a2 = String.valueOf(Fuzzy.a_predikat2);
				String a3 = String.valueOf(Fuzzy.a_predikat3);
				String a4 = String.valueOf(Fuzzy.a_predikat4);
				String a5 = String.valueOf(Fuzzy.a_predikat5);
				String a6 = String.valueOf(Fuzzy.a_predikat6);
				String a7 = String.valueOf(Fuzzy.a_predikat7);
				String a8 = String.valueOf(Fuzzy.a_predikat8);
				String a9 = String.valueOf(Fuzzy.a_predikat9);
		        rule.setText("\n (" + a1 + ") [1] IF berat badan IS ringan AND tinggi badan IS rendah THEN status gizi IS normal\n\n" +
		        		" (" + a2 + ") [2] IF berat badan IS ringan AND tinggi badan IS normal THEN status gizi IS kurus tingkat ringan\n\n" +
		        		" (" + a3 + ") [3] IF berat badan IS ringan AND tinggi badan IS tinggi THEN status gizi IS kurus tingkat berat\n\n" +
		        		" (" + a4 + ") [4] IF berat badan IS normal AND tinggi badan IS rendah THEN status gizi IS gemuk tingkat ringan\n\n" + 
		        		" (" + a5 + ") [5] IF berat badan IS normal AND tinggi badan IS normal THEN status gizi IS normal\n\n" +
		        		" (" + a6 + ") [6] IF berat badan IS normal AND tinggi badan IS tinggi THEN status gizi IS kurus tingkat ringan\n\n" +
		        		" (" + a7 + ") [7] IF berat badan IS berat AND tinggi badan IS rendah THEN status gizi IS gemuk tingkat berat\n\n" +
		        		" (" + a8 + ") [8] IF berat badan IS berat AND tinggi badan IS normal THEN status gizi IS gemuk tingkat ringan\n\n" +
		        		" (" + a9 + ") [9] IF berat badan IS berat AND tinggi badan IS tinggi THEN status gizi IS normal\n");
			}
		});
		btnAnalisis.setForeground(new Color(255, 255, 255));
		btnAnalisis.setBackground(new Color(25, 25, 112));
		btnAnalisis.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnAnalisis.setBounds(66, 276, 136, 28);
		frame.getContentPane().add(btnAnalisis);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlShadow);
		separator.setBounds(1, 90, 519, 2);
		frame.getContentPane().add(separator);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BeratBadan.setText(null);
				TinggiBadan.setText(null);
				nilaiGizi.setText(null);
				statusGizi.setText(null);
				statusGizi.setBackground(Color.white);
				a_beratBadan.setText(null);
				a_tinggiBadan.setText(null);
				rule.setText(null);
			}
		});
		btnReset.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		btnReset.setBounds(66, 309, 136, 23);
		frame.getContentPane().add(btnReset);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		panel.setBackground(SystemColor.control);
		panel.setBounds(11, 138, 240, 214);
		frame.getContentPane().add(panel);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblOutput.setBackground(SystemColor.inactiveCaptionBorder);
		lblOutput.setBounds(352, 103, 58, 28);
		frame.getContentPane().add(lblOutput);
		
		JLabel lblNilaiGizi = new JLabel("Nilai Gizi");
		lblNilaiGizi.setHorizontalAlignment(SwingConstants.LEFT);
		lblNilaiGizi.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblNilaiGizi.setBounds(283, 163, 48, 14);
		frame.getContentPane().add(lblNilaiGizi);
		
		nilaiGizi = new JTextField();
		nilaiGizi.setEditable(false);
		nilaiGizi.setHorizontalAlignment(SwingConstants.CENTER);
		nilaiGizi.setFont(new Font("Segoe UI Historic", Font.PLAIN, 11));
		nilaiGizi.setColumns(10);
		nilaiGizi.setBounds(370, 160, 118, 20);
		frame.getContentPane().add(nilaiGizi);
		
		statusGizi = new JTextField();
		statusGizi.setBackground(Color.WHITE);
		statusGizi.setForeground(SystemColor.desktop);
		statusGizi.setText("Status Gizi");
		statusGizi.setEditable(false);
		statusGizi.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		statusGizi.setHorizontalAlignment(SwingConstants.CENTER);
		statusGizi.setColumns(10);
		statusGizi.setBounds(283, 288, 205, 33);
		frame.getContentPane().add(statusGizi);
		
		JLabel lblBeratBadan_1 = new JLabel("- Berat Badan");
		lblBeratBadan_1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblBeratBadan_1.setBounds(283, 191, 74, 14);
		frame.getContentPane().add(lblBeratBadan_1);
		
		JLabel lblBeratBadan_1_1 = new JLabel("- Tinggi Badan");
		lblBeratBadan_1_1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		lblBeratBadan_1_1.setBounds(283, 216, 89, 21);
		frame.getContentPane().add(lblBeratBadan_1_1);
		
		a_beratBadan = new JTextField();
		a_beratBadan.setEditable(false);
		a_beratBadan.setBounds(370, 189, 118, 20);
		frame.getContentPane().add(a_beratBadan);
		a_beratBadan.setColumns(10);
		
		a_tinggiBadan = new JTextField();
		a_tinggiBadan.setEditable(false);
		a_tinggiBadan.setBounds(370, 213, 118, 20);
		frame.getContentPane().add(a_tinggiBadan);
		a_tinggiBadan.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(269, 138, 234, 214);
		frame.getContentPane().add(panel_1);
		
		JLabel lblKelompok = new JLabel("Kelompok 3");
		lblKelompok.setFont(new Font("Segoe UI Historic", Font.PLAIN, 11));
		lblKelompok.setForeground(SystemColor.controlShadow);
		lblKelompok.setBounds(414, 70, 74, 14);
		frame.getContentPane().add(lblKelompok);
		
		rule = new JTextArea();
		rule.setForeground(Color.DARK_GRAY);
		rule.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		rule.setEditable(false);
		rule.setBounds(10, 363, 493, 97);
		frame.getContentPane().add(rule);
		
		scrollPane = new JScrollPane(rule);
		scrollPane.setBounds(0, 350, 513, 121);
		frame.getContentPane().add(scrollPane);
	}
}
