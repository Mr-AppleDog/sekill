const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  visitedViews: state => state.tagsView.visitedViews,
  roles: state => state.user.roles,
  permissions: state => state.user.permissions,
  routes: state => state.user.routes,
  dict: state => state.dict.dict
}
export default getters
