package fr.openwide.core.infinispan.model.impl;

import java.util.Date;

import org.infinispan.remoting.transport.Address;

import fr.openwide.core.commons.util.CloneUtils;
import fr.openwide.core.infinispan.model.INode;

public class Node implements INode {

	private static final long serialVersionUID = 5121676759539404734L;

	private final Address address;

	private final String name;

	private final Date creationDate;

	private Date disconnectionDate;

	private final boolean anonymous;

	private Node(Address address, String name, Date creationDate, boolean anonymous) {
		super();
		this.name = name;
		this.address = address;
		this.creationDate = CloneUtils.clone(creationDate);
		this.anonymous = anonymous;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public Date getDisconnectionDate() {
		return disconnectionDate;
	}

	public void setDisconnectionDate(Date disconnectionDate) {
		this.disconnectionDate = CloneUtils.clone(disconnectionDate);
	}

	@Override
	public boolean isAnonymous() {
		return anonymous;
	}

	@Override
	public String toString() {
		return String.format("%s<%s-%s>", getClass().getSimpleName(), address, name);
	}

	public static final Node from(Address address, String name) {
		return new Node(address, name, new Date(), false);
	}

	public static final Node from(Address address) {
		return new Node(address, "anonymous", new Date(), true);
	}

}
