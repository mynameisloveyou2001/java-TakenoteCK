package NET.taovan;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomNoteDetails implements UserDetails{
	private Notes notes;
	public CustomNoteDetails(Notes note) {
		this.notes = note;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	public String getTieude() {
		return notes.getTieude();
	}
	public String getNoidung() {
		return notes.getNoidung();
	}
	public String getNgaytao() {
		return notes.getNgaytao();
	}
	
	

	@Override
	public boolean isAccountNonExpired() {
			return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public String getPassword() {
		return notes.getNoidung();
	}
	@Override
	public String getUsername() {
		return notes.getTieude();
		
	}
}
