package GoogleAPIs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Places")
public class Places {

	@Id
	@GeneratedValue
	@Column(name = "Places_Types")
	private String Types;
	@Column(name = "Places_Name")
	private String Name;
	
	public String getTypes() {
		return Types;
	}
	public void setTypes(String types) {
		Types = types;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
