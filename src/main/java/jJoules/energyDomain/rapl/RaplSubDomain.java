/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public abstract class RaplSubDomain extends RaplDomain {
	
	private int subSocket;

	/**
	 * @param socket
	 */
	public RaplSubDomain(int socket,int subSocket) {
		super(socket);
		this.subSocket = subSocket;
	}

	/**
	 * @return a sub socket (id of this domain)
	 */
	public int getSubSocket() {
		return this.subSocket;
	}
	
	@Override
	public String domainPath() {
		return RaplPackageDomain.RAPL_PKG_PATH_NAME+this.getSocket()+"/intel-rapl:"+
				this.getSocket()+":"+this.getSubSocket();
	}
	
}
