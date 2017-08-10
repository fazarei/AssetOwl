package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.login.model.UserDataCredentials;
import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Date;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;

import okhttp3.CipherSuite;
import okhttp3.Handshake;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;

/**
 * Created by patrickyin on 22/3/17.
 */

public class PostLoginMockFilter extends MockFilter {
    private UserDataCredentials inputUserDataCredentials;

    final static UserDataCredentials SUCCESS_LOGIN_USER = new UserDataCredentials("support@test.assetowl.com", "SecretOwl");
    final static UserDataCredentials ERROR_500_LOGIN_USER = new UserDataCredentials("http500error_tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_501_LOGIN_USER = new UserDataCredentials("http501error_tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_502_LOGIN_USER = new UserDataCredentials("http502error_tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_503_LOGIN_USER = new UserDataCredentials("http503error_tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_504_LOGIN_USER = new UserDataCredentials("http504error_tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_TIMEOUT_LOGIN_USER = new UserDataCredentials("timeouterror-tester@test.assetowl.com", "CorrectPassword");
    final static UserDataCredentials ERROR_CERTIFICATE_LOGIN_USER = new UserDataCredentials("certificate-error-tester@test.assetowl.com", "CorrectPassword");

    private final static UserDataCredentials ERROR_T_AND_C_NETWORK_ISSUE = new UserDataCredentials("network-issue@test.assetowl.com", "123456789");
    private final static UserDataCredentials ERROR_T_AND_C_ACCEPT_NETWORK_ISSUE = new UserDataCredentials("network-issue-tandc@test.assetowl.com", "123456789");

    final static String ACCESS_TOKEN_T_AND_C_NETWORK_ISSUE = "TANDCNETWORKERRORACCESSTOKEN";
    final static String ACCESS_TOKEN_T_AND_C_ACCEPT_NETWORK_ISSUE = "TANDCACCEPTNETWORKERRORACCESSTOKEN";
    final static String ACCESS_TOKEN = "THBlusBVFuJKC-9DWUh7YDRIhhnz2eVQ0vWjQRvxamYSxJIME8Fo_mnfhmu5chZ2WGUg";

    public PostLoginMockFilter(Context context, Gson gson) {
        super(context, gson);
    }

    @Override
    public void setRequest(Request request) {
        super.setRequest(request);
        inputUserDataCredentials = gson.fromJson(requestString, UserDataCredentials.class);
    }

    @Override
    public String getResponseString() {
        setResponseStatusCode(getResponseStatusCodeWithUserDataCredentials(inputUserDataCredentials));

        if (inputUserDataCredentials.equals(PostLoginMockFilter.SUCCESS_LOGIN_USER)) {
            return getSuccessResponseString();
        } else if (inputUserDataCredentials.equalsUsername(ERROR_500_LOGIN_USER) ||
                inputUserDataCredentials.equalsUsername(ERROR_501_LOGIN_USER) ||
                inputUserDataCredentials.equalsUsername(ERROR_502_LOGIN_USER) ||
                inputUserDataCredentials.equalsUsername(ERROR_503_LOGIN_USER) ||
                inputUserDataCredentials.equalsUsername(ERROR_504_LOGIN_USER)) {
            return "";
        } else if (inputUserDataCredentials.equalsUsername(ERROR_T_AND_C_NETWORK_ISSUE)) {
            return getSuccessResponseString(ACCESS_TOKEN_T_AND_C_NETWORK_ISSUE);
        } else if (inputUserDataCredentials.equalsUsername(ERROR_T_AND_C_ACCEPT_NETWORK_ISSUE)) {
            return getSuccessResponseString(ACCESS_TOKEN_T_AND_C_ACCEPT_NETWORK_ISSUE);
        } else {
            return "{\n" +
                    "  \"message\": \"The email and password you entered didn't match.\"\n" +
                    "}";
        }
    }

    @Override
    public Response getCustomResponse() {
        if (inputUserDataCredentials.equalsUsername(ERROR_TIMEOUT_LOGIN_USER)) {
            return generateTimeoutResponse();
        } else if (inputUserDataCredentials.equalsUsername(ERROR_CERTIFICATE_LOGIN_USER)) {
            setResponseStatusCode(495);
            return new Response.Builder()
                    .code(getResponseStatusCode())
                    .message("null")
                    .request(request)
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse(getContentType()), "null".getBytes()))
                    .addHeader("content-type", getContentType())
                    .handshake(Handshake.get(new SSLSession() {
                        @Override
                        public byte[] getId() {
                            return new byte[0];
                        }

                        @Override
                        public SSLSessionContext getSessionContext() {
                            return null;
                        }

                        @Override
                        public long getCreationTime() {
                            return 0;
                        }

                        @Override
                        public long getLastAccessedTime() {
                            return 0;
                        }

                        @Override
                        public void invalidate() {

                        }

                        @Override
                        public boolean isValid() {
                            return false;
                        }

                        @Override
                        public void putValue(String name, Object value) {

                        }

                        @Override
                        public Object getValue(String name) {
                            return null;
                        }

                        @Override
                        public void removeValue(String name) {

                        }

                        @Override
                        public String[] getValueNames() {
                            return new String[0];
                        }

                        @Override
                        public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
                            return new Certificate[0];
                        }

                        @Override
                        public Certificate[] getLocalCertificates() {
                            return new Certificate[0];
                        }

                        @Override
                        public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
                            return new X509Certificate[0];
                        }

                        @Override
                        public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                            return null;
                        }

                        @Override
                        public Principal getLocalPrincipal() {
                            return null;
                        }

                        @Override
                        public String getCipherSuite() {
                            return CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256.toString();
                        }

                        @Override
                        public String getProtocol() {
                            return TlsVersion.TLS_1_2.javaName();
                        }

                        @Override
                        public String getPeerHost() {
                            return null;
                        }

                        @Override
                        public int getPeerPort() {
                            return 0;
                        }

                        @Override
                        public int getPacketBufferSize() {
                            return 0;
                        }

                        @Override
                        public int getApplicationBufferSize() {
                            return 0;
                        }
                    }))
                    .build();
        }
        return null;
    }


    private int getResponseStatusCodeWithUserDataCredentials(UserDataCredentials userDataCredentials) {
        if (userDataCredentials.equals(PostLoginMockFilter.SUCCESS_LOGIN_USER) || userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_T_AND_C_NETWORK_ISSUE) || userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_T_AND_C_ACCEPT_NETWORK_ISSUE)) {
            return 200;
        } else if (userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_500_LOGIN_USER)) {
            return 500;
        } else if (userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_501_LOGIN_USER)) {
            return 501;
        } else if (userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_502_LOGIN_USER)) {
            return 502;
        } else if (userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_503_LOGIN_USER)) {
            return 503;
        } else if (userDataCredentials.equalsUsername(PostLoginMockFilter.ERROR_504_LOGIN_USER)) {
            return 504;
        } else {
            return 401;
        }
    }

    private String getSuccessResponseString() {
        return getSuccessResponseString(ACCESS_TOKEN);
    }

    private String getSuccessResponseString(String accessToken) {
        return "{\n" +
                "  \"id\": 478,\n" +
                "  \"firstName\": \"AssetOwl\",\n" +
                "  \"lastName\": \"Support\",\n" +
                "  \"accessToken\": \"" + accessToken + "\",\n" +
                "  \"refreshToken\": \"eyJ2YWx1ZSI6ImRhMjU2ODkzLWY2YTAtNDdmMy05NmFlLTVlMDgzZmMzODU3YiIsImlkIjoiNTBkNjA0MTQtZTdjZC00NWYwLWI4YWItODYyZTMzNjJlMDNjIn0=\",\n" +
                "  \"accessTokenExpiry\": " + (new Date().getTime() + 30 * 86400) + ",\n" +
                "  \"configurations\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"key\": \"LANGUAGE_CODE\",\n" +
                "      \"value\": \"en-AU\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"key\": \"REFRESH_IRS_MINS\",\n" +
                "      \"value\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"key\": \"SUPPORT_URL\",\n" +
                "      \"value\": \"https://help.assetowl.com/ipad\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"permissions\": [\n" +
                "    \"GROUP_CREATE\",\n" +
                "    \"PROJECTS_DELETE_ANY_STATE\",\n" +
                "    \"REQUEST_ANSWER\",\n" +
                "    \"REQUEST_CREATE\",\n" +
                "    \"REQUEST_DELETE\",\n" +
                "    \"REQUEST_EDIT\",\n" +
                "    \"REQUEST_SHARE_RESULTS\",\n" +
                "    \"REQUEST_VIEW_RESULTS\",\n" +
                "    \"ROLE_ASSIGN\",\n" +
                "    \"ROLE_CREATE\",\n" +
                "    \"ROLE_LIST\",\n" +
                "    \"STORE_CREATE\",\n" +
                "    \"STORE_LEVEL_CREATE\",\n" +
                "    \"STORE_LEVEL_UPDATE\",\n" +
                "    \"STORE_LEVEL_UPDATE_LAYER\",\n" +
                "    \"STORE_LIST\",\n" +
                "    \"STORE_PUBLISH\",\n" +
                "    \"STORE_UPDATE\",\n" +
                "    \"STORE_VTOUR_UPLOAD\",\n" +
                "    \"TERMS_EDIT\",\n" +
                "    \"TOS_CREATE\",\n" +
                "    \"TOS_EXEMPT\",\n" +
                "    \"USER_CREATE\",\n" +
                "    \"USER_DELETE\",\n" +
                "    \"USER_INVITE\",\n" +
                "    \"USER_LIST\",\n" +
                "    \"USER_PASSWORD_RESET\",\n" +
                "    \"USER_UPDATE\"\n" +
                "  ],\n" +
                "  \"clientAssociations\": [\n" +
                "    {\n" +
                "      \"clientId\": 28,\n" +
                "      \"clientName\": \"ALDI\",\n" +
                "      \"logoUrl\": \"https://s3-ap-southeast-2.amazonaws.com/general.assetowl.com/aldi.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 35,\n" +
                "      \"clientName\": \"Cellarbrations\",\n" +
                "      \"logoUrl\": \"https://static.assetowl.com/Cellarbrations.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 29,\n" +
                "      \"clientName\": \"Coles Express\",\n" +
                "      \"logoUrl\": \"https://s3-ap-southeast-2.amazonaws.com/general.assetowl.com/coles-express.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 26,\n" +
                "      \"clientName\": \"Coles Supermarkets\",\n" +
                "      \"logoUrl\": \"/assets/common/images/clients/COLES.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 38,\n" +
                "      \"clientName\": \"FACS\",\n" +
                "      \"logoUrl\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 34,\n" +
                "      \"clientName\": \"Housing NSW\",\n" +
                "      \"logoUrl\": \"https://static.assetowl.com/Housing_NSW.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 27,\n" +
                "      \"clientName\": \"Knowledge\",\n" +
                "      \"logoUrl\": \"https://static.assetowl.com/KnowLedge.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 36,\n" +
                "      \"clientName\": \"LAHC\",\n" +
                "      \"logoUrl\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 37,\n" +
                "      \"clientName\": \"NSW Department of Education\",\n" +
                "      \"logoUrl\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 31,\n" +
                "      \"clientName\": \"Reece\",\n" +
                "      \"logoUrl\": \"https://static.assetowl.com/REECE.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"clientId\": 24,\n" +
                "      \"clientName\": \"Woolworths\",\n" +
                "      \"logoUrl\": \"/assets/common/images/clients/WOOLWORTHS.png\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"coins\": [\n" +
                "    {\n" +
                "      \"name\": \"projects-module\",\n" +
                "      \"displayName\": \"Projects Module\",\n" +
                "      \"description\": \"Project module\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"project-outcomes\",\n" +
                "      \"displayName\": \"Projects Outcomes\",\n" +
                "      \"description\": \"View project outcomes for active/started/completed projects\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"floorplan-builder\",\n" +
                "      \"displayName\": \"Store floorplan builder\",\n" +
                "      \"description\": \"Allow users to modify floorplan layers\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"store-publish\",\n" +
                "      \"displayName\": \"Store publish\",\n" +
                "      \"description\": \"Allow users to publish store\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ir-store-results-with-floorplan\",\n" +
                "      \"displayName\": \"Floormap based answer display\",\n" +
                "      \"description\": \"Display floormap with pins when map pin answers are present\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"download-links\",\n" +
                "      \"displayName\": \"Display download link for store outcome\",\n" +
                "      \"description\": \"Display download link for individual store outcome results\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"share-ir-results\",\n" +
                "      \"displayName\": \"Display share request results button\",\n" +
                "      \"description\": \"Display share request results button\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"client-selector\",\n" +
                "      \"displayName\": \"Client Selector\",\n" +
                "      \"description\": \"Client selector in the common header to switch clients\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"google-sheet-integration\",\n" +
                "      \"displayName\": \"Display Google Sheet tab when sharing Audit\",\n" +
                "      \"description\": \"Display Google Sheet tab when sharing Audit\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"intercom\",\n" +
                "      \"displayName\": \"Intercom Support\",\n" +
                "      \"description\": \"Enables Intercom help button\",\n" +
                "      \"coinType\": \"WIP\",\n" +
                "      \"status\": \"ENABLED\",\n" +
                "      \"allowUserOverride\": false\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tosAccepted\": false \n" +
                "}";
    }
}
