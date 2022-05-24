/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author WXO
 */
public class MovieController {
    ModelMovie modelMovie;
    MovieView viewMovie;

    public MovieController(ModelMovie modelMovie, MovieView viewMovie) {
        this.modelMovie = modelMovie;
        this.viewMovie = viewMovie;
        
        if (modelMovie.getBanyakData()!=0) {
            String dataKontak[][] = modelMovie.readMovie();
            viewMovie.tabel.setModel((new JTable(dataKontak, viewMovie.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        viewMovie.btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String Judul = viewMovie.getJudul();
                double Alur = Double.parseDouble(viewMovie.getAlur());
                double Penokohan = Double.parseDouble(viewMovie.getPenokohan());
                double Akting = Double.parseDouble(viewMovie.getAkting());
                double Nilai = (Alur+Penokohan+Akting)/3;
                
                modelMovie.insertData(Judul, Alur, Penokohan, Akting, Nilai);
         
                String dataKontak[][] = modelMovie.readMovie();
                viewMovie.tabel.setModel((new JTable(dataKontak, viewMovie.namaKolom)).getModel());
            }
        });
        
        viewMovie.btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String Judul = viewMovie.getJudul();
                double Alur = Double.parseDouble(viewMovie.getAlur());
                double Penokohan = Double.parseDouble(viewMovie.getPenokohan());
                double Akting = Double.parseDouble(viewMovie.getAkting());
                double Nilai = (Alur+Penokohan+Akting)/3;
                
                modelMovie.updateMovie(Judul, Alur, Penokohan, Akting, Nilai);
                
                String dataKontak[][] = modelMovie.readMovie();
                viewMovie.tabel.setModel((new JTable(dataKontak, viewMovie.namaKolom)).getModel());
            }
        });
        
        viewMovie.btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String dataKontak[][] = modelMovie.readMovie();
                viewMovie.tabel.setModel((new JTable(dataKontak, viewMovie.namaKolom)).getModel());
            }
        });
        
        viewMovie.tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = viewMovie.tabel.getSelectedRow();
                int kolom = viewMovie.tabel.getSelectedColumn(); // ga kepake sekarang

                String dataterpilih = viewMovie.tabel.getValueAt(baris, 1).toString();

                System.out.println(dataterpilih);

                int input = JOptionPane.showConfirmDialog(null,
                        "Apa anda ingin menghapus NoHp " + dataterpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    modelMovie.deleteMovie(dataterpilih);
                    String dataKontak[][] = modelMovie.readMovie();
                    viewMovie.tabel.setModel(new JTable(dataKontak, viewMovie.namaKolom).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
    }
}
