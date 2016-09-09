package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="icon")
public class Icon implements Serializable{

	private static final long serialVersionUID = 2546590966674430116L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="icon_id")
	private int iconId;

	@Column(name="content")
	private String content;
	
	@Column(name="path")
	private String path;
	
	@Column(name="use_framework")
	private int useFramework;

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getUseFramework() {
		return useFramework;
	}

	public void setUseFramework(int useFramework) {
		this.useFramework = useFramework;
	}
	
	
	
	
	
	
}
