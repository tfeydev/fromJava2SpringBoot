# Angular Frontend (Standalone)

Built with Angular 20+ and Angular Material.

## 🚀 Development

```bash
cd angular-frontend
npm install
npm start
```

- App URL: `http://localhost:4200`
- API proxy configured in `proxy.conf.json`.

## 📁 Project Structure

```
src/
 ├── app/
 │   ├── core/navigation/   # Standalone nav component
 │   ├── pages/             # Standalone page components
 │   ├── service/           # HttpClient services
 │   └── common/hr/         # DTOs & models
 ├── assets/                # Images, logos
 ├── styles.css             # Global styles
 └── main.ts                # bootstrapApplication
```

## 📸 Assets

- Logo: `src/assets/thors-hammer-logo.jpg`
- Product images under `src/assets/images/products`

## ✅ Testing & Build

- **Unit tests:** `npm test`
- **E2E tests:** `npm run e2e`
- **Build:** `npm run build`

---

*All REST and frontend best practices applied: standalone components, Angular Material navigation, server-side pagination, DTO mapping, Lombok-processed entities, OpenAPI docs.*

