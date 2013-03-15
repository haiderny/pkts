package com.aboutsip.yajpcap.packet.sip;

import com.aboutsip.buffer.Buffer;
import com.aboutsip.yajpcap.packet.impl.ApplicationPacket;
import com.aboutsip.yajpcap.packet.sip.header.ContentTypeHeader;
import com.aboutsip.yajpcap.packet.sip.header.FromHeader;
import com.aboutsip.yajpcap.packet.sip.header.RecordRouteHeader;
import com.aboutsip.yajpcap.packet.sip.header.RouteHeader;
import com.aboutsip.yajpcap.packet.sip.header.ToHeader;
import com.aboutsip.yajpcap.packet.sip.impl.SipParseException;

/**
 * Packet representing a SIP message.
 * 
 * @author jonas@jonasborjesson.com
 * 
 */
public interface SipMessage extends ApplicationPacket {

    /**
     * The first line of a sip message, which is either a request or a response
     * line
     * 
     * @return
     */
    Buffer getInitialLine();

    /**
     * Got tired of casting the {@link SipMessage} into a {@link SipRequest} so
     * you can use this method instead. Just a short cut for:
     * 
     * <code>
     *     (SipRequest)sipMessage;
     * </code>
     * 
     * @return this but casted into a {@link SipRequest}
     * @throws ClassCastException
     *             in case this {@link SipMessage} is actually a
     *             {@link SipResponse}.
     */
    SipRequest toRequest() throws ClassCastException;

    /**
     * Got tired of casting the {@link SipMessage} into a {@link SipResponse} so
     * you can use this method instead. Just a short cut for:
     * 
     * <code>
     *     (SipResponse)sipMessage;
     * </code>
     * 
     * @return this but casted into a {@link SipResponse}
     * @throws ClassCastException
     *             in case this {@link SipMessage} is actually a
     *             {@link SipResponse}.
     */
    SipResponse toResponse() throws ClassCastException;

    /**
     * Check whether this sip message is a response or not
     * 
     * @return
     */
    boolean isResponse();

    /**
     * Check whether this sip message is a request or not
     * 
     * @return
     */
    boolean isRequest();

    /**
     * Returns the content (payload) of the {@link SipMessage} as an
     * {@link Object}. If the {@link ContentTypeHeader} indicates a content type
     * that is known (such as an sdp) then an attempt to parse the content into
     * that type is made. If the payload is unknown then a {@link Buffer}
     * representing the payload will be returned.
     * 
     * @return
     * @throws SipParseException
     *             in case anything goes wrong when trying to frame the content
     *             in any way.
     */
    Object getContent() throws SipParseException;

    /**
     * Checks whether this {@link SipMessage} is carrying anything in its
     * message body.
     * 
     * @return true if this {@link SipMessage} has a message body, false
     *         otherwise.
     */
    boolean hasContent();

    /**
     * Get the method of this sip message
     * 
     * @return
     */
    Buffer getMethod() throws SipParseException;

    /**
     * Get the header as a buffer
     * 
     * @param headerName
     *            the name of the header we wish to fetch
     * @return the header as a {@link SipHeader} or null if not found
     * @throws SipParseException
     */
    SipHeader getHeader(Buffer headerName) throws SipParseException;

    /**
     * Same as {@link #getHeader(Buffers.wrap(keyParameter)}.
     * 
     * @param headerName
     *            the name of the header we wish to fetch
     * @return the header as a {@link SipHeader} or null if not found
     * @throws SipParseException
     */
    SipHeader getHeader(String headerName) throws SipParseException;

    /**
     * Convenience method for fetching the from-header
     * 
     * @return the from header as a buffer
     * @throws SipParseException TODO
     */
    FromHeader getFromHeader() throws SipParseException;

    /**
     * Convenience method for fetching the to-header
     * 
     * @return the to header as a buffer
     */
    ToHeader getToHeader() throws SipParseException;

    /**
     * Get the top-most {@link RecordRouteHeader} header if present.
     * 
     * @return the top-most {@link RecordRouteHeader} header or null if there
     *         are no {@link RecordRouteHeader} headers found in this
     *         {@link SipMessage}.
     * @throws SipParseException
     */
    RecordRouteHeader getRecordRouteHeader() throws SipParseException;

    /**
     * Get the top-most {@link RouteHeader} header if present.
     * 
     * @return the top-most {@link RouteHeader} header or null if there are no
     *         {@link RouteHeader} headers found in this {@link SipMessage}.
     * @throws SipParseException
     */
    RouteHeader getRouteHeader() throws SipParseException;

    /**
     * Get the {@link ContentTypeHeader} for this message. If there is no
     * Content-Type header in this SIP message then null will be returned.
     * 
     * @return the {@link ContentTypeHeader} or null if there is none.
     * @throws SipParseException
     */
    ContentTypeHeader getContentTypeHeader() throws SipParseException;

    /**
     * Convenience method for fetching the call-id-header
     * 
     * @return the call-id header as a buffer
     */
    SipHeader getCallIDHeader() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * an INVITE or not. Hence, this is NOT to the method to determine whether
     * this is a INVITE Request or not!
     * 
     * @return true if the method of this message is a INVITE, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isInvite() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * a BYE or not. Hence, this is NOT to the method to determine whether this
     * is a BYE Request or not!
     * 
     * @return true if the method of this message is a BYE, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isBye() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * an ACK or not. Hence, this is NOT to the method to determine whether this
     * is an ACK Request or not!
     * 
     * @return true if the method of this message is a ACK, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isAck() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * a OPTIONS or not. Hence, this is NOT to the method to determine whether
     * this is an OPTIONS Request or not!
     * 
     * @return true if the method of this message is a OPTIONS, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isOptions() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * a MESSAGE or not. Hence, this is NOT to the method to determine whether
     * this is an MESSAGE Request or not!
     * 
     * @return true if the method of this message is a MESSAGE, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isMessage() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * a INFO or not. Hence, this is NOT to the method to determine whether this
     * is an INFO Request or not!
     * 
     * @return true if the method of this message is a INFO, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isInfo() throws SipParseException;

    /**
     * Convenience method for determining whether the method of this message is
     * a CANCEL or not
     * 
     * @return true if the method of this message is a CANCEL, false otherwise.
     * @throws SipParseException
     *             in case the method could not be parsed out of the underlying
     *             buffer.
     */
    boolean isCancel() throws SipParseException;

    /**
     * Checks whether or not this request is considered to be an "initial"
     * request, i.e., a request that does not go within a dialog.
     * 
     * @return
     * @throws SipParseException
     */
    boolean isInitial() throws SipParseException;


    /**
     * {@inheritDoc}
     * 
     * <p>
     * In the case of a SIP message the following checks are conducted: The
     * following checks are available:
     * 
     * <ul>
     * <li>ruri sip version - checks if the SIP version in the request URI is
     * supported, currently only 2.0.</li>
     * <li>ruri scheme - checks if the URI scheme of the request URI is
     * supported (sip[s]|tel[s]) by SIP-router.</li>
     * <li>required headers - checks if the minimum set of required headers to,
     * from, cseq, callid and via is present in the request.</li>
     * <li>via sip version - not working because parser fails already when
     * another version then 2.0 is present.</li>
     * <li>via protocol - not working because parser fails already if an
     * unsupported transport is present.</li>
     * <li>cseq method - checks if the method from the cseq header is equal to
     * the request method.</li>
     * <li>cseq value - checks if the number in the cseq header is a valid
     * unsigned integer.</li>
     * <li>content length - checks if the size of the body matches with the
     * value from the content length header.</li>
     * <li>expires value - checks if the value of the expires header is a valid
     * unsigned integer.</li>
     * <li>proxy require - checks if all items of the proxy require header are
     * present in the list of the extensions from the module parameter
     * proxy_require.</li>
     * 
     * <li>parse uri's - checks if the specified URIs are present and parseable
     * by the SIP-router parsers</li>
     * <li>digest credentials - Check all instances of digest credentials in a
     * message. The test checks whether there are all required digest parameters
     * and have meaningful values.</li>
     * </ul>
     * </p>
     * 
     * <p>
     * This list is taken from <a href=
     * "http://kamailio.org/docs/modules/stable/modules/sanity.html#sanity_check"
     * >Kamailio.org</a>
     * </p>
     * 
     */
    @Override
    void verify();

}
