import {AuthRole} from "../auth/auth.models";

export class User {
    id: number;
    username: string;
    active: boolean;
    roles: AuthRole[];

    public static empty(): User {
        const user = new User();
        user.username = "";
        user.roles = [];
        return user;
    }
}

export class ChangePasswordRequest {
    public oldPassword: string;
    // tslint:disable-next-line
    public password_1: string;
    // tslint:disable-next-line
    public password_2: string;
}
