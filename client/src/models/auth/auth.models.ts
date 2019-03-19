export class LoginCredentials {
    username = "";
    password = "";
}

export interface AuthTokenRefreshResponse {
    accessToken: string;
    expiresIn: number;
    sessionExpiresIn: number;
}

export class AuthRole {
    id: number;
    code: string;
    name: string;
}

export class AuthTokenPayload {
    subject: number;
    username: string;
    roles: AuthRole[];
    issuedAt: Date;
    expiresAt: Date;
    issuer: string;

    public static fromToken(token: string): AuthTokenPayload {
        const base64Payload = token.split(".")[1].replace(/-/g, "+").replace(/_/g, "/");
        const jsonPayload = JSON.parse(atob(base64Payload));
        const payload = new AuthTokenPayload();
        payload.expiresAt = new Date(jsonPayload.exp * 1000);
        payload.issuedAt = new Date(jsonPayload.iat * 1000);
        payload.roles = jsonPayload.roles;
        payload.subject = jsonPayload.sub;
        payload.username = jsonPayload.username;
        payload.issuer = jsonPayload.iss;
        return payload;
    }
}

export class AuthData {
    authenticated: boolean;
    accessToken: string;
    tokenValidity: number;
    sessionValidity: number;
    payload: AuthTokenPayload;

    public static fromResponse(resp: AuthTokenRefreshResponse): AuthData {
        const data = new AuthData();
        data.authenticated = true;
        data.accessToken = resp.accessToken;
        data.tokenValidity = resp.expiresIn;
        data.sessionValidity = resp.sessionExpiresIn;
        data.payload = AuthTokenPayload.fromToken(resp.accessToken);
        return data;
    }

    public static unauthenticated(): AuthData {
        const data = new AuthData();
        data.authenticated = false;
        data.accessToken = "resp.accessToken";
        data.tokenValidity = -1;
        data.sessionValidity = -1;
        data.payload = null;
        return data;
    }
}
