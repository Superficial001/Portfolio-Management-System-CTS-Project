package com.cognizant.portfolio_management.DailyMutualFundNAV.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class MutualFund {
	@Id
	@Column
	private String mutualFundId;
	@Column
	private String mutualFundName;
	@Column
	private double mutualFundValue;
	
	
}
