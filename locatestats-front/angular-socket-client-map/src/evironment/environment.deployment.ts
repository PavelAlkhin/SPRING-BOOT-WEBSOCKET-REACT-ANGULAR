// environment.deployment.ts
export const environment = {
    production: false,
    baseUrl: 'http://localhost:4200',
    envVar: {
        /**
         * Add environment variables you want to retriev from process
         * PORT:4200,
         * VAR_NAME: defaultValue
         */
        GOOGLE_MAP_KEY: "AIzaSyBAyMH-A99yD5fHQPz7uzqk8glNJYGEqus"
    }
};
