package NET.taovan;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "notes")
public class Notes {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long stt;
		@Column(nullable = false, length = 100)
		private String tieude;
		@Column(nullable = false, length = 1000)
		private String noidung;
		@Column(nullable = false, length = 100)
		private Date ngaytao;
		
		public Notes() {
			super();
		}

		
		public Notes(String tieude, String noidung, Date ngaytao) {
			super();
			this.tieude = tieude;
			this.noidung = noidung;
			this.ngaytao = ngaytao;
		}

		public Long getStt() {
			return stt;
		}


		public void setStt(Long stt) {
			this.stt = stt;
		}


		public String getTieude() {
			return tieude;
		}

		public void setTieude(String tieude) {
			this.tieude = tieude;
		}

		public String getNoidung() {
			return noidung;
		}

		public void setNoidung(String noidung) {
			this.noidung = noidung;
		}

		public Date getNgaytao() {
			return ngaytao;
		}

		public void setNgaytao(Date ngaytao) {
			this.ngaytao = ngaytao;
		}
		
		
}
